package com.aof.skyloreadditions.entity.client.armor;

import com.aof.skyloreadditions.SkyloreAdditions;
import com.aof.skyloreadditions.items.armor.CryoBoots;
import com.aof.skyloreadditions.items.armor.HoverBoots;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class HoverBootsModel extends AnimatedGeoModel<HoverBoots> {
    @Override
    public Identifier getModelLocation(HoverBoots object) {
        return new Identifier(SkyloreAdditions.MOD_ID, "geo/hover_boots.geo.json");
    }

    @Override
    public Identifier getTextureLocation(HoverBoots object) {
        return new Identifier(SkyloreAdditions.MOD_ID, "textures/models/armor/hover_boots.png");
    }

    @Override
    public Identifier getAnimationFileLocation(HoverBoots animatable) {
        return new Identifier(SkyloreAdditions.MOD_ID, "animations/armor_animation.json");
    }
}
