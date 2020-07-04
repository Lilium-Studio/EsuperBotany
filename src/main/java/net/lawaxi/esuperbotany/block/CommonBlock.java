package net.lawaxi.esuperbotany.block;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CommonBlock extends Block {

    public CommonBlock(String name, Material materialIn) {

        super(materialIn);

        setUnlocalizedName(name);

        ForgeRegistries.BLOCKS.register(this.setRegistryName(new ResourceLocation(name)));
        ForgeRegistries.ITEMS.register(new ItemBlock(this).setRegistryName(name));

        EsuCommons.items.add(Item.getItemFromBlock(this));
    }
}
