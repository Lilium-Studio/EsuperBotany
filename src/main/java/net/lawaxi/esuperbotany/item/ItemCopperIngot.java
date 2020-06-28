package net.lawaxi.esuperbotany.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class ItemCopperIngot extends Item {
    public ItemCopperIngot() {

        String name = "esuperbotany:copper_ingot";
        setUnlocalizedName(name);

        OreDictionary.registerOre("ingotCopper", this);
        ForgeRegistries.ITEMS.register(this.setRegistryName(name));
        ModelLoader.setCustomModelResourceLocation(this,0,new ModelResourceLocation(name, "inventory"));
    }
}
