package com.aof.skyloreadditions.items.armor;

import com.aof.skyloreadditions.armor.ModArmorMaterials;
import com.aof.skyloreadditions.handler.InputHandler;
import com.aof.skyloreadditions.items.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
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

public class WoodenJetpack extends ArmorItem implements IAnimatable {
        private final AnimationFactory factory = new AnimationFactory(this);

    public WoodenJetpack(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(itemStack);
        ItemStack itemStack2 = user.getEquippedStack(equipmentSlot);
        if (itemStack2.isEmpty()) {
            user.equipStack(equipmentSlot, itemStack.copy());
            if (!world.isClient()) {
                user.incrementStat(Stats.USED.getOrCreateStat(this));
            }

            itemStack.setCount(0);
            return TypedActionResult.success(itemStack, world.isClient());
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof PlayerEntity player) {
            ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
            Item item = chest.getItem();
            if (!chest.isEmpty() && item instanceof WoodenJetpack) {
                if (InputHandler.isHoldingUp(player)) {
                    Vec3d pos = player.getPos();
                    Vec3d facing = player.getRotationVecClient();
                    Vec3d particlePos = pos.subtract(facing);
                    world.addParticle(ParticleTypes.LARGE_SMOKE, particlePos.getX(), particlePos.getY() + .75, particlePos.getZ(), 0, 0, 0);
                    player.setVelocity(player.getVelocity().x,Math.min(.3, player.getVelocity().y + .15), player.getVelocity().z);
                    stack.damage(1, player, (p) -> p.sendEquipmentBreakStatus(EquipmentSlot.CHEST));
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

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
                boolean isWearingAll = armorList.containsAll(Arrays.asList(ModItems.WOODEN_JETPACK));
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
