package net.lawaxi.esuperbotany.utils.register;

import net.minecraft.util.text.TextFormatting;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.KnowledgeType;

public class EsuKnowledgeType {

    public static KnowledgeType ESU;

    public static void init(){
        ESU = BotaniaAPI.registerKnowledgeType("esu", TextFormatting.RED, true);
    }
}
