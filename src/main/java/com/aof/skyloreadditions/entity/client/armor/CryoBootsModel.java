package com.aof.skyloreadditions.entity.client.armor;

import com.aof.skyloreadditions.SkyloreAdditions;
import com.aof.skyloreadditions.items.armor.CryoBoots;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CryoBootsModel extends AnimatedGeoModel<CryoBoots> {
    @Override
    public Identifier getModelLocation(CryoBoots object) {
        return new Identifier(SkyloreAdditions.MOD_ID, "geo/cryo_boots.geo.json");
    }

    @Override
    public Identifier getTextureLocation(CryoBoots object) {
        return new Identifier(SkyloreAdditions.MOD_ID, "textures/models/armor/cryo_boots.png");
    }

    @Override
    public Identifier getAnimationFileLocation(CryoBoots animatable) {
        return new Identifier(SkyloreAdditions.MOD_ID, "animations/armor_animation.json");
    }
}
