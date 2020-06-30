package net.lawaxi.esuperbotany.utils.register.botania;

import net.lawaxi.esuperbotany.utils.names.FuctionalFlora;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class EsuElvenTrade {

    public static RecipeElvenTrade JOESSR;

    public static void init(){

        BotaniaAPI.registerElvenTradeRecipe(new ItemStack(EsuCommons.RESOURCE,1,2),new ItemStack(Items.PAINTING));

        ItemStack a = Item.getByNameOrId("botania:specialflower").getDefaultInstance();
        a.setStackDisplayName(I18n.format("info.joessr.any"));
        JOESSR = BotaniaAPI.registerElvenTradeRecipe(ItemBlockSpecialFlower.ofType(FuctionalFlora.JOESSR), a);
    }
}
