package com.aof.skyloreadditions.entity.client.armor;

import com.aof.skyloreadditions.items.armor.CryoBoots;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class CryoBootsRenderer extends GeoArmorRenderer<CryoBoots> {
    public CryoBootsRenderer() {
        super(new CryoBootsModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }
}
