package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.utils.names.FuctionalFlora;
import net.lawaxi.esuperbotany.utils.names.GeneratingFlora;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibOreDict;

public class EsuPetalRecipe {

    // 感谢额外植物学
    private static final String white = LibOreDict.PETAL[0], orange = LibOreDict.PETAL[1], magenta = LibOreDict.PETAL[2], lightBlue = LibOreDict.PETAL[3], yellow = LibOreDict.PETAL[4], lime = LibOreDict.PETAL[5], pink = LibOreDict.PETAL[6], gray = LibOreDict.PETAL[7], lightGray = LibOreDict.PETAL[8], cyan = LibOreDict.PETAL[9], purple = LibOreDict.PETAL[10], blue = LibOreDict.PETAL[11], brown = LibOreDict.PETAL[12], green = LibOreDict.PETAL[13], red = LibOreDict.PETAL[14], black = LibOreDict.PETAL[15];

    // 产能花
    public static RecipePetals lily;
    public static RecipePetals yanhuang;


    // 功能花
    public static RecipePetals lotuspeony;

    public static void init(){

        //7个绿色花瓣合成Lawaxi头颅噢2333~
        BotaniaAPI.registerPetalRecipe(gethead("Lawaxi"),green,green,green,green,green,green,green);

        //产能花
        lily = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(GeneratingFlora.LILY),white,white,white,white,white,white);
        yanhuang = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(GeneratingFlora.YANHUANG),red,yellow,red);

        //功能花
        lotuspeony = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(FuctionalFlora.LOTUSPEONY),red,green,red);
    }

    public static ItemStack gethead(String name){
        ItemStack head = new ItemStack(Items.SKULL,1,3);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("SkullOwner","Lawaxi");
        head.setTagCompound(nbt.copy());
        return head;
    }
}
