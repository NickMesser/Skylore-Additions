package com.aof.skyloreadditions;

import com.aof.skyloreadditions.items.ModItems;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SkyloreAdditions implements ModInitializer {
    public static final String MOD_ID = "skylore-additions";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("Skylore Additions Initialized");

        ModItems.registerModItems();
    }
}
