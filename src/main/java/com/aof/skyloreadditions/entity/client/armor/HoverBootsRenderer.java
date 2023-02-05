package com.aof.skyloreadditions.entity.client.armor;

import com.aof.skyloreadditions.items.armor.CryoBoots;
import com.aof.skyloreadditions.items.armor.HoverBoots;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class HoverBootsRenderer extends GeoArmorRenderer<HoverBoots> {
    public HoverBootsRenderer() {
        super(new HoverBootsModel());

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
