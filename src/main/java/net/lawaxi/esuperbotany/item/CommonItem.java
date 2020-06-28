package net.lawaxi.esuperbotany.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CommonItem extends Item {

    public CommonItem(String name) {
        setUnlocalizedName(name);
        ForgeRegistries.ITEMS.register(this.setRegistryName(name));
        ModelLoader.setCustomModelResourceLocation(this,0,new ModelResourceLocation(name, "inventory"));
    }
}
