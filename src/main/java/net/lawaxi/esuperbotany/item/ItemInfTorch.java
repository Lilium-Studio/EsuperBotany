package net.lawaxi.esuperbotany.item;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;

import javax.annotation.Nullable;
import java.util.List;

public class ItemInfTorch extends CommonItem implements IManaUsingItem {

    public ItemInfTorch() {
        super("esuperbotany:inftorch");
        setMaxStackSize(1);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private static final int cost = 50;
    private static final int cost_red = 400;

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }

    //改自 ItemBlock
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        IBlockState iblockstate = worldIn.getBlockState(pos);
        ItemStack me = player.getHeldItem(hand);

        boolean red = getTorch(me);
        Block torch;
        if (red) {
            torch = Blocks.REDSTONE_TORCH;
        } else
            torch = Blocks.TORCH;


        if (!iblockstate.getBlock().isReplaceable(worldIn, pos))
            pos = pos.offset(facing);

        if (!me.isEmpty() && player.canPlayerEdit(pos, facing, me) && worldIn.mayPlace(torch, pos, false, facing, player)
                && (red ? ManaItemHandler.requestManaExactForTool(me, player, cost_red, true) : ManaItemHandler.requestManaExactForTool(me, player, cost, true))) {

            IBlockState iblockstate1 = torch.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, player, hand);
            worldIn.setBlockState(pos, iblockstate1);
            //worldIn.setBlockState(pos, iblockstate1,11);

            SoundType soundtype = iblockstate1.getBlock().getSoundType(iblockstate1, worldIn, pos, player);
            worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

            return EnumActionResult.SUCCESS;
        } else
            return EnumActionResult.FAIL;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if (getTorch(stack))
            return 0D;
        else
            return 0.5D;
    }

    private boolean getTorch(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, "redstone", false);
    }

    private void switchTorch(ItemStack stack) {
        ItemNBTHelper.setBoolean(stack, "redstone", !getTorch(stack));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        if (getTorch(stack)) {
            tooltip.add(I18n.format("esupermisc.torchinfo1"));
        } else
            tooltip.add(I18n.format("esupermisc.torchinfo0"));

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }


    @SubscribeEvent
    public void leftClick(PlayerInteractEvent.LeftClickEmpty e) {
        switchTorch(e.getItemStack());
    }
}
