package net.lawaxi.esuperbotany.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;

public class Helper {

    public static void sendActionBar(EntityPlayer player,String key, Object... args){
        player.sendStatusMessage(new TextComponentTranslation(key,args), true);
    }

    public static String colorSymbol(String pre){
        return pre.replace("&","\u00a7");
    }


    public static final ItemStack gethead(String name){
        ItemStack head = new ItemStack(Items.SKULL,1,3);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("SkullOwner",name);
        head.setTagCompound(nbt.copy());
        return head;
    }

}
