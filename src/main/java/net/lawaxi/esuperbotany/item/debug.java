package net.lawaxi.esuperbotany.item;

import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class debug extends Item {

    public debug() {

        String name = "esuperbotany:debug";
        setUnlocalizedName(name);
        ForgeRegistries.ITEMS.register(this.setRegistryName(name));
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void rightClick(PlayerInteractEvent.RightClickBlock e) {

        if(e.getItemStack().getItem() == this){
            e.getEntityPlayer().sendMessage(new TextComponentString(e.getWorld().getBlockState(e.getPos()).toString()));
        }
    }


}