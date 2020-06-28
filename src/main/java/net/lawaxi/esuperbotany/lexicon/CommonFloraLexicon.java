package net.lawaxi.esuperbotany.lexicon;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lexicon.BasicLexiconEntry;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;
import vazkii.botania.common.lexicon.page.PageText;

public class CommonFloraLexicon extends BasicLexiconEntry {

    public CommonFloraLexicon(String unlocalizedName, LexiconCategory category, RecipePetals recipe) {

        super(unlocalizedName, category);
        setKnowledgeType(BotaniaAPI.basicKnowledge);
        setLexiconPages(new LexiconPage[]{new PageText("0"), new PagePetalRecipe("1", recipe)});
        setIcon(ItemBlockSpecialFlower.ofType(unlocalizedName));
    }
}
