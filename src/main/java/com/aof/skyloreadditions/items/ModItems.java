package com.aof.skyloreadditions.items;

import com.aof.skyloreadditions.SkyloreAdditions;
import com.aof.skyloreadditions.armor.ModArmorMaterials;
import com.aof.skyloreadditions.items.armor.CryoBoots;
import com.aof.skyloreadditions.items.armor.HoloWings;
import com.aof.skyloreadditions.items.armor.WoodenJetpack;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item REPULSION_CANNON = registerItem("repulsion_cannon",
            new RepulsionCannon(new FabricItemSettings().group(ItemGroup.MISC).maxDamage(200)));

    public static final Item GRAVITY_GREMNADE = registerItem("gravity_grenade",
            new GravityGrenade(new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item HOLO_WINGS = registerItem("holo_wings",
            new HoloWings(ModArmorMaterials.SKYLORE_WINGS, EquipmentSlot.CHEST, new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item CRYO_BOOTS  = registerItem("cryo_boots",
            new CryoBoots(ModArmorMaterials.CRYO_BOOTS, EquipmentSlot.FEET, new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item WOODEN_JETPACK  = registerItem("wooden_jetpack",
            new WoodenJetpack(ModArmorMaterials.WOODEN_JETPACK, EquipmentSlot.CHEST, new FabricItemSettings().group(ItemGroup.MISC)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(SkyloreAdditions.MOD_ID, name), item);
    }

    public static void registerModItems(){
        SkyloreAdditions.LOGGER.info("Registering items...");
    }
}
