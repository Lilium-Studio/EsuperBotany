package net.lawaxi.esuperbotany.item.util;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CommonItem extends Item {

    public CommonItem(String name) {
        setUnlocalizedName(name);
        ForgeRegistries.ITEMS.register(this.setRegistryName(name));
        EsuCommons.items.add(this);
    }


    protected boolean onHand(ItemStack stack, EntityPlayer player){

        if(player.getHeldItemMainhand() == stack)
            return true;

        if(player.getHeldItemOffhand() == stack)
            return true;

        return false;
    }
}
