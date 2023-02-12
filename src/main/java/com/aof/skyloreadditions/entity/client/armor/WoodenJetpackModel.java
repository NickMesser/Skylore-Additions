package com.aof.skyloreadditions.entity.client.armor;

import com.aof.skyloreadditions.SkyloreAdditions;
import com.aof.skyloreadditions.items.armor.HoloWings;
import com.aof.skyloreadditions.items.armor.WoodenJetpack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WoodenJetpackModel extends AnimatedGeoModel<WoodenJetpack> {
    @Override
    public Identifier getModelLocation(WoodenJetpack object) {
        return new Identifier(SkyloreAdditions.MOD_ID, "geo/wooden_jetpack.geo.json");
    }

    @Override
    public Identifier getTextureLocation(WoodenJetpack object) {
        return new Identifier(SkyloreAdditions.MOD_ID, "textures/models/armor/wooden_jetpack.png");
    }

    @Override
    public Identifier getAnimationFileLocation(WoodenJetpack animatable) {
        return new Identifier(SkyloreAdditions.MOD_ID, "animations/armor_animation.json");
    }
}
