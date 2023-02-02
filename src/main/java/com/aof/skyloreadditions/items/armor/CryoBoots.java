package com.aof.skyloreadditions.items.armor;

import com.aof.skyloreadditions.blocks.ModBlocks;
import com.aof.skyloreadditions.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
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

public class CryoBoots extends ArmorItem implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    public CryoBoots(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof PlayerEntity player){
            ItemStack equippedStack = player.getEquippedStack(EquipmentSlot.FEET);
            if(equippedStack != stack || equippedStack.getItem() != this)
                return;


            for (int i = 0; i < 3; i++) { // check 3 blocks below the player
                BlockPos pos = player.getBlockPos().down(i);
                if(!world.getBlockState(pos).isAir())
                    return;
            }
            ItemStack iceStack = null;
            for (ItemStack inventoryStack: player.getInventory().main){
                if(inventoryStack.getItem() == ModBlocks.DIMENSIONAL_ICE.asItem()){
                    iceStack = inventoryStack;
                }
            }

            if (iceStack == null)
                return;

            iceStack.setCount(iceStack.getCount() - 1);
            BlockPos pos = player.getBlockPos().down();

            world.setBlockState(pos, ModBlocks.DIMENSIONAL_ICE.getDefaultState());
            world.createAndScheduleBlockTick(pos, ModBlocks.DIMENSIONAL_ICE, MathHelper.nextInt(player.getRandom(), 60, 120));
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
            boolean isWearingAll = armorList.containsAll(Arrays.asList(ModItems.CRYO_BOOTS));
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
}
