package net.lawaxi.esuperbotany.block;

import net.lawaxi.esuperbotany.EsuperBotany;
import net.lawaxi.esuperbotany.block.tile.TileManaStorage;
import net.lawaxi.esuperbotany.item.block.ItemManaStorage;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockManaStorage extends Block {

    public static final int default_mana = 50000;

    public BlockManaStorage() {

        super(Material.ROCK);
        setSoundType(SoundType.SNOW);
        setHarvestLevel("pickaxe", 2);
        this.setHardness(5.0F);
        OreDictionary.registerOre("blockMana", this);

        String name = "esuperbotany:manastorage";
        setUnlocalizedName(name);
        ForgeRegistries.BLOCKS.register(this.setRegistryName(new ResourceLocation(name)));
        ForgeRegistries.ITEMS.register(new ItemManaStorage(this).setRegistryName(name));
        EsuCommons.items.add(Item.getItemFromBlock(this));
    }

    @Override
    public void onLanded(World worldIn, Entity entityIn) {

        //借鉴自匠魂2:
        // https://github.com/SlimeKnights/TinkersConstruct
        // BlockSlimeCongealed.java

        if (entityIn instanceof EntityLivingBase || entityIn instanceof EntityItem) {

            int mana = getMana(worldIn,new BlockPos((int)entityIn.posX,(int)entityIn.posY,(int)entityIn.posZ).add(0,-1,0));
            if(mana!=default_mana)
                entityIn.motionY *= mana/default_mana;

            entityIn.motionY *= -1.2F;
            entityIn.fallDistance = 0;

            if (entityIn instanceof EntityItem)
                entityIn.onGround = false;
        }
    }

    //挖掘掉落
    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ItemStack a = new ItemStack(EsuCommons.MANASTORAGE);
        int mana = getMana(world,pos);
        EsuperBotany.logger.warn(mana);
        if(mana!=default_mana){
            ((ItemManaStorage)a.getItem()).setMana(a,mana);
        }
        drops.add(a);
    }

    //创造模式鼠标中键点击
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {

        ItemStack a = new ItemStack(EsuCommons.MANASTORAGE);
        int mana = getMana(world,pos);
        if(mana!=default_mana){
            ((ItemManaStorage)a.getItem()).setMana(a,mana);
        }
        return a;

    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileManaStorage();
    }

    public int getMana(IBlockAccess world,BlockPos pos){

        if(world.getTileEntity(pos) instanceof TileManaStorage){
            TileManaStorage tile = (TileManaStorage)world.getTileEntity(pos);
            return tile.mana;
        }
        return default_mana;
    }

    public boolean setMana(World world,BlockPos pos,int mana){

        if(world.getTileEntity(pos) instanceof TileManaStorage){
            TileManaStorage tile = (TileManaStorage)world.getTileEntity(pos);
            tile.mana = mana;
        }

        return false;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        setMana(worldIn,pos,((ItemManaStorage)stack.getItem()).getMana(stack));
    }
}
