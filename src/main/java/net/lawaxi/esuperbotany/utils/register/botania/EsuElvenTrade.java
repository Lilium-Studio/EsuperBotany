package net.lawaxi.esuperbotany.utils.register.botania;

import net.lawaxi.esuperbotany.api.Helper;
import net.lawaxi.esuperbotany.utils.names.FuctionalFlora;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class EsuElvenTrade {

    public static RecipeElvenTrade JOESSR;
    public static RecipeElvenTrade LAWAXI;

    public static void init(){

        BotaniaAPI.registerElvenTradeRecipe(new ItemStack(ModItems.manaResource,1,13),new ItemStack(ModItems.manaResource,1,3));
        BotaniaAPI.registerElvenTradeRecipe(new ItemStack(EsuCommons.RESOURCE,1,2),new ItemStack(Items.PAINTING));

        ItemStack a = new ItemStack(Item.getByNameOrId("botania:specialflower"));
        try {
            a.setStackDisplayName(I18n.format("info.specialflora.any"));
        }catch (Error e){

        }
        JOESSR = BotaniaAPI.registerElvenTradeRecipe(ItemBlockSpecialFlower.ofType(FuctionalFlora.JOESSR), a);


        ItemStack b = new ItemStack(Items.SKULL);
        try{
            b.setStackDisplayName(I18n.format("info.playerskull.any"));
        }catch (Error e){

        }
        LAWAXI = BotaniaAPI.registerElvenTradeRecipe(Helper.gethead("Lawaxi"),b);
    }
}
