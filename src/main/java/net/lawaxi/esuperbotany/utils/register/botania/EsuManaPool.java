package net.lawaxi.esuperbotany.utils.register.botania;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;

public class EsuManaPool {

    public static void init(){

        BotaniaAPI.registerManaInfusionRecipe(new ItemStack(EsuCommons.RESOURCE,1, 0),new ItemStack(Items.EMERALD),10000);
        BotaniaAPI.registerManaInfusionRecipe(new ItemStack(EsuCommons.MANAEMERALDBLOCK),new ItemStack(Blocks.EMERALD_BLOCK),90000);
    }
}
