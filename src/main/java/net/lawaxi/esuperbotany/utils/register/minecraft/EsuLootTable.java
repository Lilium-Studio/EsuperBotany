package net.lawaxi.esuperbotany.utils.register.minecraft;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class EsuLootTable {

    public static void init(){
        LootTableList.register(new ResourceLocation("esuperbotany:bhsf"));
    }
}
