package com.aof.skyloreadditions.entity.client.armor;

import com.aof.skyloreadditions.SkyloreAdditions;
import com.aof.skyloreadditions.items.armor.CryoBoots;
import com.aof.skyloreadditions.items.armor.HoloWings;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HoloWingsModel extends AnimatedGeoModel<HoloWings> {
    @Override
    public Identifier getModelLocation(HoloWings object) {
        return new Identifier(SkyloreAdditions.MOD_ID, "geo/holo_wings.geo.json");
    }

    @Override
    public Identifier getTextureLocation(HoloWings object) {
        return new Identifier(SkyloreAdditions.MOD_ID, "textures/models/armor/holo_wings.png");
    }

    @Override
    public Identifier getAnimationFileLocation(HoloWings animatable) {
        return new Identifier(SkyloreAdditions.MOD_ID, "animations/armor_animation.json");
    }
}
