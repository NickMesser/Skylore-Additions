package com.aof.skyloreadditions.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    // This is the name of the enum, and the name of the material
    SKYLORE_WINGS("holo_wings", 37, new int[]{3, 6, 2, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0F, 0F, () -> {
        return Ingredient.ofItems(Items.SHULKER_SHELL);
    }),
    CRYO_BOOTS("cryo_boots", 37, new int[]{2, 6, 2, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0F, 0F, () -> {
        return Ingredient.ofItems(Items.BLUE_ICE);
    }),
    WOODEN_JETPACK("wooden_jetpack", 30, new int[]{0, 0, 2, 0}, 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0F, 0F, () -> {
        return Ingredient.ofItems(Items.COAL_BLOCK);
    }),
    HOVER_BOOTS("hover_boots", 40, new int[]{3, 0, 0, 0}, 20, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 0F, 0F, () -> {
        return Ingredient.ofItems(Items.SHULKER_SHELL);
    })
    ;

    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Lazy<Ingredient> repairIngredientSupplier;

    private ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = new Lazy(repairIngredientSupplier);
    }

    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
    }

    public int getProtectionAmount(EquipmentSlot slot) {
        return this.protectionAmounts[slot.getEntitySlotId()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredientSupplier.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
