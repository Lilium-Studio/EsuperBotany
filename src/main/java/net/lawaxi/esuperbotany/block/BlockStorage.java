package net.lawaxi.esuperbotany.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlockStorage extends Block {

    public static final int size = 1;
    // 0: 魔力绿宝石块

    public BlockStorage() {

        super(Material.ROCK);

        String name = "esuperbotany:storage";
        setUnlocalizedName(name);
        setHarvestLevel("pickaxe",3);
        this.setHardness(10.0F);

        ForgeRegistries.BLOCKS.register(this.setRegistryName(new ResourceLocation(name)));
        ForgeRegistries.ITEMS.register(new ItemBlock(this).setRegistryName(name));


        for(int i=0;i<size;i++){
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),i,new ModelResourceLocation(name+i,"inventory"));
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        for(int i=0;i<size;i++){
            items.add(new ItemStack(this,1,i));
        }
    }

}
