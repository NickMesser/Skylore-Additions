package com.aof.skyloreadditions.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;


public class RepulsionCannon extends Item {
    public RepulsionCannon(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(world.isClient)
            return super.use(world,user, hand);

       var lookDirection = user.getRotationVector().normalize();
       double pushStrength = .25f;
       Box box = Box.from(user.getPos()).expand(10);

       var test = world.getOtherEntities(user, box);
       for (Entity entity: test){
           if(user.canSee(entity)){
               var entityRotation = entity.getPos();
               var relativeRot = entityRotation.subtract(user.getRotationVector().normalize()).negate();
               entity.setVelocity(entityRotation.multiply(pushStrength, relativeRot.y, relativeRot.z));
           }
       }


        user.sendMessage(new LiteralText("Using my repulsion cannon!"), false);

        return super.use(world, user, hand);
    }
}
