package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.lexicon.CommonItemInfoLexicon;
import net.lawaxi.esuperbotany.lexicon.CommonFloraLexicon;
import net.lawaxi.esuperbotany.utils.names.FuctionalFlora;
import net.lawaxi.esuperbotany.utils.names.GeneratingFlora;
import net.lawaxi.esuperbotany.utils.register.botania.EsuPetalRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;

public class EsuLexicon {

    //菜单
    public static LexiconCategory categoryESU;

    //产能花
    public static LexiconEntry LILY;
    public static LexiconEntry YANHUANG;


    //功能花
    public static LexiconEntry LOTUSPOEONY;


    public static void init(){

        //自定义菜单
        categoryESU = new LexiconCategory("category.Botania:esu");
        categoryESU.setIcon(new ResourceLocation("esuperbotany","textures/gui/categories/esu.png"));
        categoryESU.setPriority(0);
        BotaniaAPI.addCategory(categoryESU);

        //产能花
        LILY = new CommonFloraLexicon(GeneratingFlora.LILY, categoryESU,EsuPetalRecipe.lily);
        YANHUANG = new CommonFloraLexicon(GeneratingFlora.YANHUANG, categoryESU, EsuPetalRecipe.yanhuang);

        //功能花
        LOTUSPOEONY = new CommonFloraLexicon(FuctionalFlora.LOTUSPEONY,categoryESU,EsuPetalRecipe.lotuspeony);

        //其他
        new CommonItemInfoLexicon("+1srod",1,true,BotaniaAPI.relicKnowledge,new ItemStack(EsuCommons.ONESROD));
        new CommonItemInfoLexicon("oldeaterrod",1,true,BotaniaAPI.relicKnowledge,new ItemStack(EsuCommons.OLDEATER));
    }
}
