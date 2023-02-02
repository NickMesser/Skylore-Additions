package com.aof.skyloreadditions.entity.client.armor;

import com.aof.skyloreadditions.items.armor.CryoBoots;
import com.aof.skyloreadditions.items.armor.HoloWings;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class HoloWingsRenderer extends GeoArmorRenderer<HoloWings> {
    public HoloWingsRenderer() {
        super(new HoloWingsModel());

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
