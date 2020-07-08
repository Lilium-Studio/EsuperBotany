package net.lawaxi.esuperbotany.item.block;


import net.lawaxi.esuperbotany.block.BlockManaStorage;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.item.IManaDissolvable;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.network.PacketBotaniaEffect;
import vazkii.botania.common.network.PacketHandler;

import javax.annotation.Nullable;
import java.util.List;

public class ItemManaStorage extends ItemBlock implements IManaDissolvable {

    private static final String itemnbt = "mana";

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

    public void setMana(ItemStack stack,int mana){
        ItemNBTHelper.setInt(stack,itemnbt,mana);
    }

    public int getMana(ItemStack stack){
        return Math.abs(ItemNBTHelper.getInt(stack,itemnbt, BlockManaStorage.default_mana));
    }
}