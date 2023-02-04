package com.aof.skyloreadditions.items.armor;

import com.aof.skyloreadditions.handler.InputHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
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
                    world.addParticle(ParticleTypes.LARGE_SMOKE, player.getX() + .1, player.getY() + 1, player.getZ() - .1, 0, 0, 0);
                    player.setVelocity(player.getVelocity().x, 0.1, player.getVelocity().z);
                    stack.damage(1, player, (p) -> p.sendEquipmentBreakStatus(EquipmentSlot.CHEST));
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
