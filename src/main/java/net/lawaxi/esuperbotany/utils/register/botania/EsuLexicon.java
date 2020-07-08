package net.lawaxi.esuperbotany.utils.register.botania;

import net.lawaxi.esuperbotany.api.Helper;
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
import vazkii.botania.common.lexicon.page.*;

public class EsuLexicon {

    //菜单
    public static LexiconCategory categoryESU;
    public static LexiconEntry LILY;
    public static LexiconEntry YANHUANG;
    public static LexiconEntry LOTUSPEONY;
    public static LexiconEntry IRRIGATOR;
    public static LexiconEntry JOESSR;

    public static void init(){

        //自定义菜单
        categoryESU = new LexiconCategory("category.Botania:esu");
        categoryESU.setIcon(new ResourceLocation("esuperbotany","textures/gui/categories/esu.png"));
        categoryESU.setPriority(0);
        BotaniaAPI.addCategory(categoryESU);

        //产能花
        LILY = new CommonFloraLexicon(GeneratingFlora.LILY, BotaniaAPI.categoryGenerationFlowers,EsuPetalRecipe.lily);
        YANHUANG = new CommonFloraLexicon(GeneratingFlora.YANHUANG, BotaniaAPI.categoryGenerationFlowers, EsuPetalRecipe.yanhuang);

        //功能花
        LOTUSPEONY =new CommonFloraLexicon(FuctionalFlora.LOTUSPEONY,BotaniaAPI.categoryFunctionalFlowers,EsuPetalRecipe.lotuspeony);
        IRRIGATOR = new CommonFloraLexicon(FuctionalFlora.IRRIGATOR,BotaniaAPI.categoryFunctionalFlowers,EsuPetalRecipe.irrigator);

        //其他
        new CommonItemInfoLexicon("+1srod",1,true,BotaniaAPI.relicKnowledge,new ItemStack(EsuCommons.ONESROD));
        new CommonItemInfoLexicon("oldeaterrod",1,true,BotaniaAPI.relicKnowledge,new ItemStack(EsuCommons.OLDEATER));
        new CommonItemInfoLexicon("expellorod",1,true,BotaniaAPI.relicKnowledge,new ItemStack(EsuCommons.EXPELLOROD));
        new CommonItemInfoLexicon("manabow",1,true,BotaniaAPI.relicKnowledge,new ItemStack(EsuCommons.MANABOW));
        //new CommonItemInfoLexicon("manaaxe",1,true,BotaniaAPI.relicKnowledge,new ItemStack(EsuCommons.MANAAXE));

        JOESSR = new BasicLexiconEntry(FuctionalFlora.JOESSR,categoryESU);
        JOESSR.setIcon(ItemBlockSpecialFlower.ofType(FuctionalFlora.JOESSR));
        JOESSR.setKnowledgeType(BotaniaAPI.elvenKnowledge);
        JOESSR.setLexiconPages(new LexiconPage[]{new PageText("0"),new PageElvenRecipe("1",EsuElvenTrade.JOESSR),new PageText("2"),new PageMultiblock("3",EsuMultiBlockSet.EMPEROR),new PageMultiblock("4",EsuMultiBlockSet.EMPEROR2)});

        LexiconEntry redscarf = new BasicLexiconEntry("redScarf",BotaniaAPI.categoryBaubles);
        redscarf.setIcon(new ItemStack(EsuCommons.COSMETIC,1,0));
        redscarf.setKnowledgeType(BotaniaAPI.basicKnowledge);
        redscarf.setLexiconPages(new LexiconPage[]{new PageText("0"),new PagePetalRecipe("1",EsuPetalRecipe.redScarf)});

        LexiconEntry flowerCollector = new BasicLexiconEntry("flowerCollector",categoryESU);
        flowerCollector.setIcon(new ItemStack(EsuCommons.COSMETIC,1,1));
        flowerCollector.setKnowledgeType(BotaniaAPI.basicKnowledge);
        flowerCollector.setLexiconPages(new LexiconPage[]{new PageText("0"),new PagePetalRecipe("1",EsuPetalRecipe.flowerCollector)});

        LexiconEntry enderman = new BasicLexiconEntry("enderman",BotaniaAPI.categoryMisc);
        enderman.setIcon(Helper.gethead("MHF_Enderman"));
        enderman.setKnowledgeType(BotaniaAPI.basicKnowledge);
        enderman.setLexiconPages(new LexiconPage[]{new PageText("0")});

        LexiconEntry inftorch = new BasicLexiconEntry("inftorch",BotaniaAPI.categoryTools);
        inftorch.setIcon(new ItemStack(EsuCommons.INFTORCH));
        inftorch.setKnowledgeType(BotaniaAPI.elvenKnowledge);
        inftorch.setLexiconPages(new LexiconPage[]{new PageText("0"),new PageCraftingRecipe("1",new ResourceLocation("esuperbotany","inftorch"))});

    }
}
