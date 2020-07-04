package net.lawaxi.esuperbotany.item;

import net.minecraftforge.oredict.OreDictionary;

public class ItemCopperIngot extends CommonItem {
    public ItemCopperIngot() {

        super ("esuperbotany:copper_ingot");
        OreDictionary.registerOre("ingotCopper", this);
    }
}
