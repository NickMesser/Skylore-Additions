package com.aof.skyloreadditions.client;

import com.aof.skyloreadditions.blocks.ModBlocks;
import com.aof.skyloreadditions.entity.client.armor.CryoBootsRenderer;
import com.aof.skyloreadditions.entity.client.armor.HoloWingsRenderer;
import com.aof.skyloreadditions.entity.client.armor.HoverBootsRenderer;
import com.aof.skyloreadditions.entity.client.armor.WoodenJetpackRenderer;
import com.aof.skyloreadditions.handler.InputHandler;
import com.aof.skyloreadditions.items.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;



@Environment(EnvType.CLIENT)
public class SkyloreAdditionsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(SkyloreAdditionsClient::clientTick);

        GeoArmorRenderer.registerArmorRenderer(new CryoBootsRenderer(), ModItems.CRYO_BOOTS);
        GeoArmorRenderer.registerArmorRenderer(new HoloWingsRenderer(), ModItems.HOLO_WINGS);
        GeoArmorRenderer.registerArmorRenderer(new HoverBootsRenderer(), ModItems.HOVER_BOOTS);
        GeoArmorRenderer.registerArmorRenderer(new WoodenJetpackRenderer(), ModItems.WOODEN_JETPACK);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CLEAR_BLOCK, RenderLayer.getTranslucent());

    }

    private static void clientTick(MinecraftClient client) {
        PlayerEntity player = client.player;
        if(player == null) return;
        Boolean jump = client.options.jumpKey.isPressed();
        InputHandler.update(player, jump);
    }

}
