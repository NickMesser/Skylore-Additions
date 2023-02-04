package com.aof.skyloreadditions.client;

import com.aof.skyloreadditions.entity.client.armor.CryoBootsRenderer;
import com.aof.skyloreadditions.entity.client.armor.HoloWingsRenderer;
import com.aof.skyloreadditions.items.ModItems;
import com.aof.skyloreadditions.items.armor.WoodenJetpack;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.NetworkUtils;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;



@Environment(EnvType.CLIENT)
public class SkyloreAdditionsClient implements ClientModInitializer {
    private static KeyBinding keyBinding;
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(SkyloreAdditionsClient::clientTick);

        GeoArmorRenderer.registerArmorRenderer(new CryoBootsRenderer(), ModItems.CRYO_BOOTS);
        GeoArmorRenderer.registerArmorRenderer(new HoloWingsRenderer(), ModItems.HOLO_WINGS);


    }

    private static void clientTick(MinecraftClient client) {
        PlayerEntity player = client.player;
        if(player == null) return;
        ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
        Item item = chest.getItem();
        if(item instanceof WoodenJetpack){
            if(client.options.jumpKey.isPressed()){
                player.setVelocity(player.getVelocity().x, 0.1, player.getVelocity().z);
                chest.setDamage(chest.getDamage() + 10);
            }
        }
    }

}
