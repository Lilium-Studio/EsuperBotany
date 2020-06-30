package net.lawaxi.esuperbotany.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import vazkii.botania.api.item.IManaDissolvable;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.common.network.PacketBotaniaEffect;
import vazkii.botania.common.network.PacketHandler;

public class BlockManaStorage extends Block {

    public static final int per = 50000;

    public BlockManaStorage() {

        super(Material.ROCK);

        setSoundType(SoundType.SNOW);
        setHarvestLevel("pickaxe",3);
        this.setHardness(5.0F);

        String name = "esuperbotany:manastorage";
        setUnlocalizedName(name);

        ForgeRegistries.BLOCKS.register(this.setRegistryName(new ResourceLocation(name)));
        ForgeRegistries.ITEMS.register(new ItemManaStorage(this).setRegistryName(name));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),0,new ModelResourceLocation(name,"inventory"));
    }

    @Override
    public void onLanded(World worldIn, Entity entityIn) {

        //借鉴自匠魂2:
        // https://github.com/SlimeKnights/TinkersConstruct
        // BlockSlimeCongealed.java

        if (entityIn instanceof EntityLivingBase || entityIn instanceof EntityItem) {

            entityIn.motionY *= -1.2F;
            entityIn.fallDistance = 0;

            if(entityIn instanceof EntityItem)
                entityIn.onGround = false;
        }
    }

    public class ItemManaStorage extends ItemBlock implements IManaDissolvable {

        public ItemManaStorage(Block block) {
            super(block);
        }

        //执行方法参见 TilePool.class
        //噢我的天哪 这代码抄的与黑莲花有什么区别

        @Override
        public void onDissolveTick(IManaPool iManaPool, ItemStack itemStack, EntityItem entityItem) {

            if(!iManaPool.isFull()) {

                PacketHandler.sendToNearby(entityItem.world, entityItem.getPosition(), new PacketBotaniaEffect(PacketBotaniaEffect.EffectType.BLACK_LOTUS_DISSOLVE, entityItem.getPosition().getX(), entityItem.getPosition().getY() + 0.5, entityItem.getPosition().getZ()));
                itemStack.shrink(1);
                iManaPool.recieveMana(per);
            }
        }
    }
}
