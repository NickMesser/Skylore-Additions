package com.aof.skyloreadditions.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
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

        ItemStack stack = user.getStackInHand(hand);

       double pushStrength = .5f;
       Box box = Box.from(user.getPos()).expand(10);

       var entityList = world.getOtherEntities(user, box);
       for (Entity entity: entityList){
           var entityPos = entity.getPos().subtract(0,5,0);
           var oppositeDirection = user.getPos().subtract(entityPos).multiply(-1);
           entity.addVelocity(oppositeDirection.x * pushStrength, oppositeDirection.y * pushStrength, oppositeDirection.z * pushStrength);
       }

       stack.setDamage(stack.getDamage() + 100);

        return super.use(world, user, hand);
    }
}
