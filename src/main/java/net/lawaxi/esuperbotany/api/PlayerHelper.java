package net.lawaxi.esuperbotany.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PlayerHelper {

    public static void giveItem(EntityPlayer player, ItemStack stack){
        if(!player.addItemStackToInventory(stack))
            player.dropItem(stack, false);
    }
}
