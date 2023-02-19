package com.aof.skyloreadditions.entity.client.armor;

import com.aof.skyloreadditions.items.armor.HoloWings;
import com.aof.skyloreadditions.items.armor.WoodenJetpack;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class WoodenJetpackRenderer extends GeoArmorRenderer<WoodenJetpack> {
    public WoodenJetpackRenderer() {
        super(new WoodenJetpackModel());

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
