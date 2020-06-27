package net.lawaxi.esuperbotany.common.lexicon;

import net.lawaxi.esuperbotany.utils.register.EsuLexicon;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.KnowledgeType;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lexicon.BasicLexiconEntry;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;
import vazkii.botania.common.lexicon.page.PageText;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class CommonArticleLexicon extends BasicLexiconEntry {

    public CommonArticleLexicon(String unlocalizedName, int page, KnowledgeType type,@Nullable ItemStack icon) {

        super(unlocalizedName, EsuLexicon.categoryESU);
        setKnowledgeType(type);
        setLexiconPages(create(page));
        if(icon!=null)
            setIcon(icon);
    }

    public static LexiconPage[] create(int page){
        ArrayList<LexiconPage> q = new ArrayList<>();
        for(int i=0;i<page;i++) {
            q.add(new PageText(String.valueOf(i)));
        }
        return q.toArray(new LexiconPage[0]);
    }
}
