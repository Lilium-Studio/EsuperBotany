package net.lawaxi.esuperbotany.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Random;

public class CopperBlock extends Block{

    public CopperBlock() {

        super(Material.ROCK);

        String name = "esuperbotany:copper_block";
        setUnlocalizedName(name);

        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe",2);

        OreDictionary.registerOre("blockCopper", this);
        ForgeRegistries.BLOCKS.register(this.setRegistryName(new ResourceLocation(name)));
        ForgeRegistries.ITEMS.register(new ItemBlock(this).setRegistryName(name));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),0,new ModelResourceLocation(name,"inventory"));

    }
}
