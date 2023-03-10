package com.aof.skyloreadditions;

import com.aof.skyloreadditions.blocks.ModBlocks;
import com.aof.skyloreadditions.handler.InputHandler;
import com.aof.skyloreadditions.items.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.server.ServerStopCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SkyloreAdditions implements ModInitializer {
    public static final String MOD_ID = "skylore-additions";
    public static final String NAME = "Skylore Additions";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("Skylore Additions Initialized");

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ServerStopCallback.EVENT.register(server -> InputHandler.clear());
    }
}
