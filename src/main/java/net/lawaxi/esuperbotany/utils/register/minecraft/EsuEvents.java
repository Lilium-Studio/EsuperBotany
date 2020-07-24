package net.lawaxi.esuperbotany.utils.register.minecraft;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EsuEvents {

    //enderman
    @SubscribeEvent
    public void rightClick(EnderTeleportEvent e) {
        if(!e.getEntityLiving().getEntityWorld().isRemote) {
            if (e.getEntityLiving() instanceof EntityEnderman &&  !canTeleport(e.getEntityLiving().getEntityWorld().getBlockState(
            new BlockPos(e.getEntityLiving().getPosition().getX(),e.getEntityLiving().getPosition().getY(),e.getEntityLiving().getPosition().getZ()).add(0,-1,0)
            ).getBlock()))


                e.setCanceled(true);
        }
    }

    private static final boolean canTeleport(Block block){

        if(block == Blocks.AIR)
            return false;

        if(block == Blocks.SLIME_BLOCK || block == EsuCommons.MANASTORAGE)
            return false;

        return true;

    }


    /*
    @SubscribeEvent
    public void lootLoad(LootTableLoadEvent evt) {

        if (evt.getName().toString().equals("minecraft:chests/simple_dungeon") // 地牢
           ||evt.getName().toString().equals("minecraft:chests/village_blacksmith") //铁匠铺
           ||evt.getName().toString().equals("minecraft:chests/end_city_treasure") //末地城
           ||evt.getName().toString().equals("minecraft:chests/nether_bridge")) //地狱堡垒
        {

        }
    }*/

}
