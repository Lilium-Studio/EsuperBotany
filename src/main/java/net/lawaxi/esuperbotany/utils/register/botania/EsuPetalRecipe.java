package net.lawaxi.esuperbotany.utils.register.botania;

import net.lawaxi.esuperbotany.api.Helper;
import net.lawaxi.esuperbotany.utils.names.FuctionalFlora;
import net.lawaxi.esuperbotany.utils.names.GeneratingFlora;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibOreDict;

public class EsuPetalRecipe {

    // 感谢额外植物学
    private static final String white = LibOreDict.PETAL[0]
            , orange = LibOreDict.PETAL[1]
            , magenta = LibOreDict.PETAL[2]
            , lightBlue = LibOreDict.PETAL[3]
            , yellow = LibOreDict.PETAL[4]
            , lime = LibOreDict.PETAL[5]
            , pink = LibOreDict.PETAL[6]
            , gray = LibOreDict.PETAL[7]
            , lightGray = LibOreDict.PETAL[8]
            , cyan = LibOreDict.PETAL[9]
            , purple = LibOreDict.PETAL[10]
            , blue = LibOreDict.PETAL[11]
            , brown = LibOreDict.PETAL[12]
            , green = LibOreDict.PETAL[13]
            , red = LibOreDict.PETAL[14]
            , black = LibOreDict.PETAL[15];

    private static final String runeWater = LibOreDict.RUNE[0]
            ,runeFire = LibOreDict.RUNE[1]
            ,runeEarth = LibOreDict.RUNE[2]
            ,runeAir = LibOreDict.RUNE[3]
            ,runeSpring = LibOreDict.RUNE[4]
            ,runeSummer = LibOreDict.RUNE[5]
            ,runeAutumn = LibOreDict.RUNE[6]
            ,runeWinter = LibOreDict.RUNE[7]
            ,runeMana = LibOreDict.RUNE[8]
            ,runeLust = LibOreDict.RUNE[9]
            ,runeGluttony = LibOreDict.RUNE[10]
            ,runeGreed = LibOreDict.RUNE[11]
            ,runeSloth = LibOreDict.RUNE[12]
            ,runeWrath = LibOreDict.RUNE[13]
            ,runeEnvy = LibOreDict.RUNE[14]
            ,runePride = LibOreDict.RUNE[15];

    // 产能花
    public static RecipePetals lily;
    public static RecipePetals yanhuang;

    // 功能花
    public static RecipePetals lotuspeony;
    public static RecipePetals irrigator;
    public static RecipePetals degausser;
    public static RecipePetals cucurbit;

    //其他
    public static RecipePetals redScarf;
    public static RecipePetals flowerCollector;
    public static RecipePetals LEON;

    public static void init(){

        LEON = BotaniaAPI.registerPetalRecipe(Helper.gethead("CMOleon"),blue,blue,blue,blue,blue,blue,blue);

        //产能花
        lily = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(GeneratingFlora.LILY),white,white,white,white,white,white,white,runePride);
        yanhuang = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(GeneratingFlora.YANHUANG),red,yellow,red,runeLust);

        //功能花
        lotuspeony = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(FuctionalFlora.LOTUSPEONY),red,red,red,green,green,green,runeSpring);
        irrigator = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(FuctionalFlora.IRRIGATOR),blue,blue,blue,lightBlue,lightBlue,lightBlue,runeWater);
        degausser = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(FuctionalFlora.DEGAUSSER),new ItemStack(Items.COMPASS),new ItemStack(Items.COMPASS),new ItemStack(Items.COMPASS),runeSloth);
        cucurbit = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(FuctionalFlora.CUCURBIT),green,green,green,purple,purple,purple,runeSummer);

        //其他
        redScarf = BotaniaAPI.registerPetalRecipe(new ItemStack(EsuCommons.COSMETIC,1,0),red,red,blue,white);
        flowerCollector = BotaniaAPI.registerPetalRecipe(new ItemStack(EsuCommons.COSMETIC,1,1),white,orange,magenta,lightBlue,yellow,lime,pink,gray,lightGray,cyan,purple,blue,brown,green,red,black);
    }

}

