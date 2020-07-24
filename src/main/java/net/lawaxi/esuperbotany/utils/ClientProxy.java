package net.lawaxi.esuperbotany.utils;

import net.lawaxi.esuperbotany.utils.client.EsuEntityRenders;
import net.lawaxi.esuperbotany.utils.client.EsuItemModels;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends Proxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        super.preInit(event);
        EsuEntityRenders.init();
        EsuItemModels.init();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);


        Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
            @Override
            public int colorMultiplier(ItemStack stack, int tintIndex) {
                return tintIndex >0 ? EsuCommons.FOOD.getColor(stack) :  -1 ;
            }
        },EsuCommons.FOOD);
    }
}
