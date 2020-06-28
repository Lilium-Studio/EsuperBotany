package net.lawaxi.esuperbotany.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class BlockCopperBlock extends Block {

    public BlockCopperBlock() {

        super(Material.ROCK);

        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe",2);
        this.setHardness(10.0F);

        String name = "esuperbotany:copper_block";
        setUnlocalizedName(name);

        OreDictionary.registerOre("blockCopper", this);
        ForgeRegistries.BLOCKS.register(this.setRegistryName(new ResourceLocation(name)));
        ForgeRegistries.ITEMS.register(new ItemBlock(this).setRegistryName(name));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),0,new ModelResourceLocation(name,"inventory"));

    }
}
