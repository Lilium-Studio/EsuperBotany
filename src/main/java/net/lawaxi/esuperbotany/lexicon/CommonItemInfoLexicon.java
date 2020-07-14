package net.lawaxi.esuperbotany.lexicon;

import net.lawaxi.esuperbotany.utils.register.EsuLexicon;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.lexicon.KnowledgeType;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.common.lexicon.BasicLexiconEntry;
import vazkii.botania.common.lexicon.page.PageCraftingRecipe;
import vazkii.botania.common.lexicon.page.PageText;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class CommonItemInfoLexicon extends BasicLexiconEntry {

    public CommonItemInfoLexicon(String unlocalizedName, int page, boolean hasRecipe, KnowledgeType type, @Nullable ItemStack icon, @Nullable LexiconCategory category) {

        super(unlocalizedName, category== null ? EsuLexicon.categoryESU : category);
        setKnowledgeType(type);

        //文章
        ArrayList<LexiconPage> q = new ArrayList<>();
        for(int i=0;i<page;i++) {
            q.add(new PageText(String.valueOf(i)));
        }

        //配方
        if(hasRecipe)
            q.add(new PageCraftingRecipe(String.valueOf(page),new ResourceLocation("esuperbotany",unlocalizedName)));


        setLexiconPages(q.toArray(new LexiconPage[0]));
        if(icon!=null)
            setIcon(icon);
    }
}
