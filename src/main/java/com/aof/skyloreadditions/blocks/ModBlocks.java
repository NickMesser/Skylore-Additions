package com.aof.skyloreadditions.blocks;

import com.aof.skyloreadditions.SkyloreAdditions;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block DIMENSIONAL_ICE = registerBlock("dimensional_ice",
            new DimensionalIce(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()), ItemGroup.MISC);

    public static final Block CLEAR_BLOCK = registerBlock("clear_block",
            new ClearBlock(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool().nonOpaque()), ItemGroup.MISC);

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(SkyloreAdditions.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(SkyloreAdditions.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerModBlocks() {
        SkyloreAdditions.LOGGER.info("Registering ModBlocks for " + SkyloreAdditions.MOD_ID);
    }
}
