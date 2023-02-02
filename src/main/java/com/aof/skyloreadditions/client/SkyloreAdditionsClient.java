package com.aof.skyloreadditions.client;

import com.aof.skyloreadditions.entity.client.armor.CryoBootsRenderer;
import com.aof.skyloreadditions.entity.client.armor.HoloWingsRenderer;
import com.aof.skyloreadditions.items.ModItems;
import com.eliotlash.mclib.math.functions.classic.Mod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Environment(EnvType.CLIENT)
public class SkyloreAdditionsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        GeoArmorRenderer.registerArmorRenderer(new CryoBootsRenderer(), ModItems.CRYO_BOOTS);
        GeoArmorRenderer.registerArmorRenderer(new HoloWingsRenderer(), ModItems.HOLO_WINGS);
    }
}
