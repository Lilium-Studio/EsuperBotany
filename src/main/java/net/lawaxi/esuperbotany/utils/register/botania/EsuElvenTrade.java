package net.lawaxi.esuperbotany.utils.register.botania;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;

public class EsuElvenTrade {

    public static void init(){

        BotaniaAPI.registerElvenTradeRecipe(new ItemStack(EsuCommons.RESOURCE,1,2),new ItemStack(Items.PAINTING));
    }
}
