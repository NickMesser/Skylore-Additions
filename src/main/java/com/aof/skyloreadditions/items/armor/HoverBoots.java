package com.aof.skyloreadditions.items.armor;

import com.aof.skyloreadditions.blocks.ModBlocks;
import com.aof.skyloreadditions.items.ModItems;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HoverBoots extends ArmorItem implements IAnimatable {
    private float currentCooldown = 0;
    private final float maxCooldown = 150;
    private final float maxUseTime = 75;
    private float currentUseTime = 0;

    private Boolean isUsing = false;
    private float stillTime = 0;

    private BlockPos lastBlockPosition = new BlockPos(0,0,0);

    private final AnimationFactory factory = new AnimationFactory(this);
    public HoverBoots(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!stack.hasNbt())
            write_nbt(stack);
        read_nbt(stack);


        if(entity instanceof PlayerEntity player){
            ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
            if(boots.getItem() instanceof HoverBoots hoverBoots){
                if(!checkCanUse(stack))
                {
                    updateCooldown(stack, currentCooldown - 1);
                    return;
                }

                if(!isUsing && checkCanUse(stack)){
                    for (int i = 0; i < 2; i++) { // check 3 blocks below the player and returns if its anything but air
                        BlockPos pos = player.getBlockPos().down(i);
                        if(!world.getBlockState(pos).isAir())
                            return;
                    }
                }
                isUsing = true;
                if(currentUseTime < maxUseTime){
                    updateUseTime(stack, currentUseTime + 1);
                }


                if(currentUseTime >= maxUseTime)
                    setCoolDown(stack);

                BlockPos currentPosition = player.getBlockPos();

                if(currentPosition.getY() > lastBlockPosition.getY())
                    setCoolDown(stack);

                if(currentPosition.equals(lastBlockPosition)){
                    updateStillTime(stack, stillTime += 1);
                    if(stillTime >= 5){
                        setCoolDown(stack);
                    }
                }
                else {
                    stillTime = 0;
                    updateStillTime(stack, 0);
                }
                updatePosition(stack, currentPosition);

                BlockPos pos = player.getBlockPos().down();
                
                if(!world.getBlockState(pos).isAir())
                    setCoolDown(stack);

                world.setBlockState(pos, ModBlocks.CLEAR_BLOCK.getDefaultState());
                world.createAndScheduleBlockTick(pos, ModBlocks.CLEAR_BLOCK,2);
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    // Predicate runs every frame
    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        // This is all the extradata this event carries. The livingentity is the entity
        // that's wearing the armor. The itemstack and equipmentslottype are self
        // explanatory.
        LivingEntity livingEntity = event.getExtraDataOfType(LivingEntity.class).get(0);

        // Always loop the animation but later on in this method we'll decide whether or
        // not to actually play it
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));

        // If the living entity is an armorstand just play the animation nonstop
        if (livingEntity instanceof ArmorStandEntity) {
            return PlayState.CONTINUE;
        }

        // The entity is a player, so we want to only play if the player is wearing the
        // full set of armor
        else if (livingEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) livingEntity;

            // Get all the equipment, aka the armor, currently held item, and offhand item
            List<Item> equipmentList = new ArrayList<>();
            player.getItemsEquipped().forEach((x) -> equipmentList.add(x.getItem()));

            // elements 2 to 6 are the armor so we take the sublist. Armorlist now only
            // contains the 4 armor slots
            List<Item> armorList = equipmentList.subList(2, 6);

            // Make sure the player is wearing all the armor. If they are, continue playing
            // the animation, otherwise stop
            boolean isWearingAll = armorList.containsAll(Arrays.asList(ModItems.HOVER_BOOTS));
            return isWearingAll ? PlayState.CONTINUE : PlayState.STOP;
        }
        return PlayState.STOP;
    }

    // All you need to do here is add your animation controllers to the
    // AnimationData
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new LiteralText("§7§oAllows a short period of hovering."));
        super.appendTooltip(stack, world, tooltip, context);
    }

    public boolean checkCanUse(ItemStack stack) {
        Boolean canUse = true;
        if(currentCooldown > 0) {
            canUse = false;
        } else {
            canUse = true;
        }

        return canUse;
    }

    public void setCoolDown(ItemStack stack) {
        updateCooldown(stack, maxCooldown);
        updateUseTime(stack, 0);
        isUsing = false;
    }

    public void read_nbt(ItemStack stack) {
        NbtCompound tag = stack.getOrCreateNbt();
        if(tag.contains("lastBlockPosition")){
            lastBlockPosition = BlockPos.fromLong(tag.getLong("lastBlockPosition"));
        }

        if(tag.contains("stillTime")){
            this.stillTime = tag.getFloat("stillTime");
        }

        lastBlockPosition = new BlockPos(currentX, currentY, currentZ);

        if(tag.contains("currentUseTime")){
            this.currentUseTime = tag.getFloat("currentUseTime");
        }

        if (tag.contains("cooldown")) {
            this.currentCooldown = tag.getFloat("cooldown");
        }
        else {
            this.currentCooldown = 0;
        }
    }

    private void write_nbt(ItemStack stack) {
        NbtCompound tag = stack.getOrCreateNbt();
        tag.putLong("lastBlockPosition", lastBlockPosition.asLong());
        tag.putFloat("currentUseTime", currentUseTime);
        tag.putFloat("cooldown", currentCooldown);
        tag.putFloat("stillTime", stillTime);
    }

    public void updateCooldown(ItemStack stack, float cooldown) {
        NbtCompound tag = stack.getOrCreateNbt();
        tag.putFloat("cooldown", cooldown);
    }

    public void updateUseTime(ItemStack stack, float useTime) {
        if (currentUseTime >= maxUseTime) {
            useTime = 0;
        }

        NbtCompound tag = stack.getOrCreateNbt();
        tag.putFloat("currentUseTime", useTime);
    }

    private void updatePosition(ItemStack stack, BlockPos pos) {
        NbtCompound tag = stack.getOrCreateNbt();
        tag.putLong("lastBlockPosition", pos.asLong());
    }

    public void updateStillTime(ItemStack stack, float stillTime) {
        NbtCompound tag = stack.getOrCreateNbt();
        tag.putFloat("stillTime", stillTime);
    }
}
