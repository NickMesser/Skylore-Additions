package com.aof.skyloreadditions.items.armor;

import com.aof.skyloreadditions.handler.InputHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.world.World;

public class WoodenJetpack extends ArmorItem {
    public WoodenJetpack(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        if(entity instanceof PlayerEntity player) {
            ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
            Item item = chest.getItem();
            if (!chest.isEmpty() && item instanceof WoodenJetpack) {
                if (InputHandler.isHoldingUp(player)) {
                    player.setVelocity(player.getVelocity().x, 0.1, player.getVelocity().z);
                    stack.damage(1, player, (p) -> p.sendEquipmentBreakStatus(EquipmentSlot.CHEST));
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if(user instanceof PlayerEntity player){
            player.sendMessage(new LiteralText("You are using the Wooden Jetpack!"), false);
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }
}
