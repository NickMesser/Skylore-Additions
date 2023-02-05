package com.aof.skyloreadditions.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlassBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class ClearBlock extends GlassBlock {
    public ClearBlock(Settings settings) {
        super(settings);
    }
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
        if(world.getBlockState(pos).getBlock() != Blocks.AIR){
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        super.scheduledTick(state, world, pos, random);
    }
}
