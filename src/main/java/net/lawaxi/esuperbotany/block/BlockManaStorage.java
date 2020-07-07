package net.lawaxi.esuperbotany.block;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.item.IManaDissolvable;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.network.PacketBotaniaEffect;
import vazkii.botania.common.network.PacketHandler;

import javax.annotation.Nullable;
import java.util.List;

public class BlockManaStorage extends Block {

    public static final int default_mana = 50000;
    private static final String itemnbt = "mana";
//    private static final IProperty blockproperty = PropertyInteger.create("mana",Integer.MIN_VALUE,Integer.MAX_VALUE);

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

            IBlockState state = worldIn.getBlockState(entityIn.getPosition().add(0,1,0));
            if(state.getBlock().equals(this))
            {
                if(getMana(state)!=default_mana)
                    entityIn.motionY *= getMana(state)/default_mana;
            }

            entityIn.motionY *= -1.2F;
            entityIn.fallDistance = 0;

            if (entityIn instanceof EntityItem)
                entityIn.onGround = false;
        }
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        ItemStack a = new ItemStack(EsuCommons.MANASTORAGE);
        int mana = getMana(state);
        if(mana!=default_mana){
            ItemNBTHelper.setInt(a,itemnbt,mana);
        }
        drops.add(a);
    }

    private int getMana(IBlockState state){
        /*
        String string = state.getProperties().getOrDefault(blockproperty,default_mana).toString();
        EsuperBotany.logger.warn(string);
        return Integer.valueOf(string);*/
        return default_mana;
    }

    public class ItemManaStorage extends ItemBlock implements IManaDissolvable {

        public ItemManaStorage(Block block) {
            super(block);
        }

        @Override
        public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
            tooltip.add(I18n.format("tile.esuperbotany:manastorage.lore")+getMana(stack));
        }

        //执行方法参见 TilePool.class
        //噢我的天哪 这代码抄的与黑莲花有什么区别

        @Override
        public void onDissolveTick(IManaPool iManaPool, ItemStack itemStack, EntityItem entityItem) {

            if (!iManaPool.isFull() && !entityItem.world.isRemote) {

                PacketHandler.sendToNearby(entityItem.world, entityItem.getPosition(), new PacketBotaniaEffect(PacketBotaniaEffect.EffectType.BLACK_LOTUS_DISSOLVE, entityItem.getPosition().getX(), entityItem.getPosition().getY() + 0.5, entityItem.getPosition().getZ()));
                iManaPool.recieveMana(getMana(itemStack));
                itemStack.shrink(1);
            }
        }

        private int getMana(ItemStack stack){
            return ItemNBTHelper.getInt(stack,itemnbt,default_mana);
        }

        /*
        @Override
        public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {


            boolean a = super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);
            if(a)
            {
                if(getMana(stack)!=default_mana){
                    world.setBlockState(pos, newState.withProperty(blockproperty,getMana(stack)), 11);
                }
            }
            return a;
        }*/
    }

}
