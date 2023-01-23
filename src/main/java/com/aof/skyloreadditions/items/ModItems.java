package com.aof.skyloreadditions.items;

import com.aof.skyloreadditions.SkyloreAdditions;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.data.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item REPULSION_CANNON = registerItem("repulsion_cannon",
            new RepulsionCannon(new FabricItemSettings().group(ItemGroup.MISC)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(SkyloreAdditions.MOD_ID, name), item);
    }

    public static void registerModItems(){
        SkyloreAdditions.LOGGER.info("Registering items...");
    }
}
