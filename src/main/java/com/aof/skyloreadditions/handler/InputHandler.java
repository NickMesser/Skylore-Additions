package com.aof.skyloreadditions.handler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;

import java.util.HashMap;
import java.util.Map;

public class InputHandler {
    private static final Map<PlayerEntity, Boolean> HOLDING_UP = new HashMap<>();


    public static boolean isHoldingUp(PlayerEntity player) {
        return HOLDING_UP.getOrDefault(player, false);
    }

    public void removePlayer(PlayerEntity player) {
        HOLDING_UP.remove(player);
    }

    public static void clear() {
        HOLDING_UP.clear();
    }
    public static void update(PlayerEntity player, boolean holdingUp) {
        HOLDING_UP.put(player, holdingUp);
    }

    public static void onLogout(PlayerEntity player) {
        HOLDING_UP.remove(player);
    }

    public static void onDimensionChange(PlayerEntity player) {
        HOLDING_UP.remove(player);
    }
}
