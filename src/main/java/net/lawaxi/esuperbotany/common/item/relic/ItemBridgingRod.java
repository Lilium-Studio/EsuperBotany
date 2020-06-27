package net.lawaxi.esuperbotany.common.item.relic;

import net.lawaxi.esuperbotany.utils.Helper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileBifrost;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBridgingRod extends ItemRelic2 implements IManaUsingItem, IRelic {

    public ItemBridgingRod() {
        super("esuperbotany:bridgingrod");
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }

    @SubscribeEvent
    public void rightClick(PlayerInteractEvent.RightClickBlock e) {


        if(e.getItemStack().getItem() == this){
            BlockPos newpos = e.getPos().add((int)e.getHitVec().x,(int)e.getHitVec().y,(int)e.getHitVec().z);

            if(e.getWorld().isAirBlock(newpos)) {
                if (e.getWorld().getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(newpos)).size() == 0) {
                    if (ManaItemHandler.requestManaExactForTool(e.getItemStack(), e.getEntityPlayer(), 20, false)) {

                        e.getWorld().setBlockState(newpos, ModBlocks.bifrost.getDefaultState());
                        ((TileBifrost) e.getWorld().getTileEntity(newpos)).ticks = 5;
                    }
                }
            }

        }

    }


    @SubscribeEvent
    public void leftClick(PlayerInteractEvent.LeftClickBlock e) {
        if(e.getItemStack().getItem() == this){
            if(e.getWorld().getTileEntity(e.getPos())!=null){
                if(e.getWorld().getTileEntity(e.getPos()) instanceof TileBifrost){
                    ((TileBifrost) e.getWorld().getTileEntity(e.getPos())).ticks = 0;
                }
            }
        }

    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.ITALIC + I18n.format(getUnlocalizedName()+".lore"));
    }

}
