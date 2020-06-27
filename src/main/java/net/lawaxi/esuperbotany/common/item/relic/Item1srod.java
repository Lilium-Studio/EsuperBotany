package net.lawaxi.esuperbotany.common.item.relic;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.block.tile.TileBifrost;

import javax.annotation.Nullable;
import java.util.List;

public class Item1srod extends ItemRelic2 implements IManaUsingItem, IRelic {

    private static final int cost = 1926;
    private static final float per = 2.0F;

    public Item1srod() {
        super("esuperbotany:+1srod");
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if(target.getHealth()<target.getMaxHealth()) {
            if (ManaItemHandler.requestManaExactForTool(stack, playerIn, cost, false)) {
                playerIn.swingArm(hand);
                target.heal(per);
                return true;
            }
        }
        return false;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.ITALIC + I18n.format(getUnlocalizedName()+".lore"));
    }

    @SubscribeEvent
    public void rightClick(PlayerInteractEvent.RightClickItem e) {

        if(e.getItemStack().getItem() == this && e.getEntityPlayer().shouldHeal()) {

            if (ManaItemHandler.requestManaExactForTool(e.getItemStack(), e.getEntityPlayer(), cost, false)) {
                e.getEntityPlayer().swingArm(e.getHand());
                e.getEntityPlayer().heal(per);
            }
        }
    }
}
