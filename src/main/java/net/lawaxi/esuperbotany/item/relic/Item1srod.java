package net.lawaxi.esuperbotany.item.relic;

import net.lawaxi.esuperbotany.api.EntityHelper;
import net.lawaxi.esuperbotany.api.Helper;
import net.lawaxi.esuperbotany.item.util.CommonItemRelic;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.block.ModBlocks;

import java.util.Random;

public class Item1srod extends CommonItemRelic implements IManaUsingItem, IRelic {

    private static final int cost = 1926;
    private static final float per = 2.0F;

    public Item1srod() {
        super("esuperbotany:+1srod",true,DamgeType.HURT);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        ItemStack me = playerIn.getHeldItem(handIn);
        EntityLivingBase target = EntityHelper.findTarget(playerIn,EntityLivingBase.class,2);

        if(me.isEmpty())
            return new ActionResult<>(EnumActionResult.PASS,me);

        if(!ManaItemHandler.requestManaExactForTool(me, playerIn, cost, false))
        {
            Helper.sendActionBar(playerIn, "info.+1srod.failed3");
            return new ActionResult<>(EnumActionResult.FAIL,me);
        }


        if(target!=null){
            //恢复别人

            if (target.getHealth() < target.getMaxHealth()) {
                ManaItemHandler.requestManaExactForTool(me, playerIn, cost, true);

                EntityHelper.particleAround(target,EnumParticleTypes.VILLAGER_HAPPY,(target.height>1F ? true : false));

                if(!playerIn.getEntityWorld().isRemote) {
                    target.heal(per);

                }else{
                    playerIn.swingArm(handIn);
                }
                Helper.sendActionBar(playerIn, "info.+1srod.success1");
                return new ActionResult<>(EnumActionResult.SUCCESS,me);

            } else {

                Helper.sendActionBar(playerIn, "info.+1srod.failed1");
                return new ActionResult<>(EnumActionResult.FAIL,me);
            }

        }else{

            //恢复自己
            if (playerIn.getHealth() < playerIn.getMaxHealth()) {

                ManaItemHandler.requestManaExactForTool(me, playerIn, cost, true);

                EntityHelper.particleAround(playerIn,EnumParticleTypes.VILLAGER_HAPPY,false);

                if(!worldIn.isRemote){
                    playerIn.heal(per);
                }else
                    playerIn.swingArm(handIn);


                Helper.sendActionBar(playerIn, "info.+1srod.success2");
                return new ActionResult<>(EnumActionResult.SUCCESS,me);

            } else {

                Helper.sendActionBar(playerIn, "info.+1srod.failed2");
                return new ActionResult<>(EnumActionResult.FAIL,me);
            }
        }

    }


    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {


        ItemStack me = player.getHeldItem(hand);

        if (!me.isEmpty()) {
            if ( worldIn.getBlockState(pos).getBlock() == Blocks.GOLD_BLOCK) {

                worldIn.playEvent(2001, pos, Block.getStateId(Blocks.GOLD_BLOCK.getDefaultState()));

                if (!worldIn.isRemote) {

                    //破化金块
                    worldIn.setBlockToAir(pos);

                    //生成养老金
                    Entity a = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(EsuCommons.RESOURCE, 1, 3));
                    ((EntityItem) a).setDefaultPickupDelay();
                    worldIn.spawnEntity(a);

                } else {
                    player.swingArm(hand);
                }

                return EnumActionResult.SUCCESS;

            }
            else if(worldIn.getBlockState(pos).getBlock() == ModBlocks.bifrost){
                worldIn.setBlockState(pos,ModBlocks.bifrostPerm.getDefaultState());
                EntityHelper.particleSimple(worldIn,pos,EnumParticleTypes.END_ROD);

                if(worldIn.isRemote)
                    player.swingArm(hand);

                return EnumActionResult.SUCCESS;
            }

            return EnumActionResult.FAIL;
        }

        return EnumActionResult.PASS;
    }


    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }

    // 改编自村民交易结束代码

    protected Random rand = new Random();


}
