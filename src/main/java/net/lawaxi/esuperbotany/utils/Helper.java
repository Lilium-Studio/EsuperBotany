package net.lawaxi.esuperbotany.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class Helper {

    public static BlockPos getClick(BlockPos pre, EnumFacing facing){
        int addx = 0;
        int addy = 0;
        int addz = 0;

        if(facing == EnumFacing.DOWN)
            addy--;
        else if(facing == EnumFacing.UP)
            addy++;
        else if(facing == EnumFacing.SOUTH)
            addz++;
        else if(facing == EnumFacing.NORTH)
            addz--;
        else if(facing == EnumFacing.EAST)
            addx++;
        else if(facing == EnumFacing.WEST)
            addx--;

        return pre.add(addx,addy,addz);
    }

    public static void sendActionBar(EntityPlayer player,String key){
        player.sendStatusMessage(new TextComponentTranslation(key), true);
    }

    public static String colorSymbol(String pre){
        return pre.replace("&","\\u00a7");
    }
}
