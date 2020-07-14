package net.lawaxi.esuperbotany.block;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Random;

public class BlockCopperOre extends BlockOre {

    public BlockCopperOre() {

        super(MapColor.STONE);

        String name = "esuperbotany:copper_ore";
        setUnlocalizedName(name);
        setHarvestLevel("pickaxe",2);
        this.setHardness(10.0F);

        OreDictionary.registerOre("oreCopper", this);
        ForgeRegistries.BLOCKS.register(this.setRegistryName(new ResourceLocation(name)));
        ForgeRegistries.ITEMS.register(new ItemBlock(this).setRegistryName(name));

        EsuCommons.items.add(Item.getItemFromBlock(this));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return EsuCommons.COPPERINGOT;
    }


    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {

        Random rand = world instanceof World ? ((World)world).rand : new Random();
        if(this.getItemDropped(state, rand, fortune) != Item.getItemFromBlock(this)){
            return MathHelper.getInt(rand, 1, 4);
        }
        return 0;
    }
}
