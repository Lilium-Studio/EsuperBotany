package net.lawaxi.esuperbotany.utils.register.botania;

import net.lawaxi.esuperbotany.lexicon.CommonFloraLexicon;
import net.lawaxi.esuperbotany.lexicon.CommonItemInfoLexicon;
import net.lawaxi.esuperbotany.utils.names.FuctionalFlora;
import net.lawaxi.esuperbotany.utils.names.GeneratingFlora;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lexicon.BasicLexiconEntry;
import vazkii.botania.common.lexicon.page.PageElvenRecipe;
import vazkii.botania.common.lexicon.page.PageMultiblock;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;
import vazkii.botania.common.lexicon.page.PageText;

public class EsuLexicon {

    //菜单
    public static LexiconCategory categoryESU;
    public static LexiconEntry LILY;
    public static LexiconEntry YANHUANG;
    public static LexiconEntry LOTUSPEONY;
    public static LexiconEntry JOESSR;

    public static void init(){

        //自定义菜单
        categoryESU = new LexiconCategory("category.Botania:esu");
        categoryESU.setIcon(new ResourceLocation("esuperbotany","textures/gui/categories/esu.png"));
        categoryESU.setPriority(0);
        BotaniaAPI.addCategory(categoryESU);

        //产能花
        LILY= new CommonFloraLexicon(GeneratingFlora.LILY, categoryESU,EsuPetalRecipe.lily);
        YANHUANG= new CommonFloraLexicon(GeneratingFlora.YANHUANG, categoryESU, EsuPetalRecipe.yanhuang);

        //功能花
        LOTUSPEONY=new CommonFloraLexicon(FuctionalFlora.LOTUSPEONY,categoryESU,EsuPetalRecipe.lotuspeony);

        //其他
        new CommonItemInfoLexicon("+1srod",1,true,BotaniaAPI.relicKnowledge,new ItemStack(EsuCommons.ONESROD));
        new CommonItemInfoLexicon("oldeaterrod",1,true,BotaniaAPI.relicKnowledge,new ItemStack(EsuCommons.OLDEATER));

        JOESSR = new BasicLexiconEntry(FuctionalFlora.JOESSR,categoryESU);
        JOESSR.setIcon(ItemBlockSpecialFlower.ofType(FuctionalFlora.JOESSR));
        JOESSR.setKnowledgeType(BotaniaAPI.elvenKnowledge);
        JOESSR.setLexiconPages(new LexiconPage[]{new PageText("0"),new PageElvenRecipe("1",EsuElvenTrade.JOESSR),new PageText("2"),new PageMultiblock("3",EsuMultiBlockSet.EMPEROR)});

        LexiconEntry redscarf = new BasicLexiconEntry("redScarf",categoryESU);
        redscarf.setIcon(new ItemStack(EsuCommons.COSMETIC,1,0));
        redscarf.setKnowledgeType(BotaniaAPI.basicKnowledge);
        redscarf.setLexiconPages(new LexiconPage[]{new PageText("0"),new PagePetalRecipe("1",EsuPetalRecipe.redScarf)});

        LexiconEntry flowerCollector = new BasicLexiconEntry("flowerCollector",categoryESU);
        flowerCollector.setIcon(new ItemStack(EsuCommons.COSMETIC,1,1));
        flowerCollector.setKnowledgeType(BotaniaAPI.basicKnowledge);
        flowerCollector.setLexiconPages(new LexiconPage[]{new PageText("0"),new PagePetalRecipe("1",EsuPetalRecipe.flowerCollector)});

    }
}
