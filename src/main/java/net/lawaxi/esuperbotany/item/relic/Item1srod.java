package net.lawaxi.esuperbotany.item.relic;

import net.lawaxi.esuperbotany.api.Helper;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

import java.util.Random;

public class Item1srod extends CommonItemRelic implements IManaUsingItem, IRelic {

    private static final int cost = 1926;
    private static final float per = 2.0F;

    public Item1srod() {
        super("esuperbotany:+1srod",true,1);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void rightClick(PlayerInteractEvent.RightClickEmpty e) {

        if (!e.getItemStack().isEmpty()) {

            if (e.getItemStack().getItem() == this) {

                if (e.getEntityPlayer().getHealth() < e.getEntityPlayer().getMaxHealth()) {

                    if (ManaItemHandler.requestManaExactForTool(e.getItemStack(), e.getEntityPlayer(), cost, false)) {

                        if(!e.getWorld().isRemote){
                            e.getEntityPlayer().heal(per);

                        }else{
                            spawnParticles(e.getEntity());
                            e.getEntityPlayer().swingArm(e.getHand());
                        }

                        Helper.sendActionBar(e.getEntityPlayer(), "info.+1srod.success2");

                    } else {

                        Helper.sendActionBar(e.getEntityPlayer(), "info.+1srod.failed3");
                    }
                } else {

                    Helper.sendActionBar(e.getEntityPlayer(), "info.+1srod.failed2");
                }

            }
        }
    }
    @SubscribeEvent
    public void rightClick(PlayerInteractEvent.RightClickBlock e) {


        if (!e.getItemStack().isEmpty()) {
            if (e.getItemStack().getItem() == this && e.getWorld().getBlockState(e.getPos()).getBlock() == Blocks.GOLD_BLOCK) {

                e.getWorld().playEvent(2001, e.getPos(), Block.getStateId(Blocks.GOLD_BLOCK.getDefaultState()));

                if (!e.getWorld().isRemote) {

                    //破化金块
                    e.getWorld().setBlockToAir(e.getPos());

                    //生成养老金
                    Entity a = new EntityItem(e.getWorld(), e.getPos().getX(), e.getPos().getY(), e.getPos().getZ(), new ItemStack(EsuCommons.RESOURCE, 1, 3));
                    ((EntityItem) a).setDefaultPickupDelay();
                    e.getWorld().spawnEntity(a);

                } else {
                    e.getEntityPlayer().swingArm(e.getHand());
                }

            }
        }
    }

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }


    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {

        if (target.getHealth() < target.getMaxHealth()) {
            if (ManaItemHandler.requestManaExactForTool(stack, playerIn, cost, false)) {

                if(!playerIn.getEntityWorld().isRemote) {
                    //服务端
                    target.heal(per);
                }else{
                    //客户端
                    spawnParticles(target);
                    playerIn.swingArm(hand);
                }
                Helper.sendActionBar(playerIn, "info.+1srod.success1");

            } else {

                Helper.sendActionBar(playerIn, "info.+1srod.failed3");
            }
        } else {

            Helper.sendActionBar(playerIn, "info.+1srod.failed1");
        }

        //此处return ture就不会触发右击空气的事件

        return true;
    }


    // 改编自村民交易结束代码

    protected Random rand = new Random();

    @SideOnly(Side.CLIENT)
    private void spawnParticles(Entity entity)
    {
        for (int i = 0; i < 5; ++i)
        {
            double d0 = this.rand.nextGaussian() * 0.02D;
            double d1 = this.rand.nextGaussian() * 0.02D;
            double d2 = this.rand.nextGaussian() * 0.02D;
            entity.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, entity.posX + (double)(this.rand.nextFloat() * entity.width * 2.0F) - (double)entity.width, entity.posY + 1.0D + (double)(this.rand.nextFloat() * entity.height), entity.posZ + (double)(this.rand.nextFloat() * entity.width * 2.0F) - (double)entity.width, d0, d1, d2);
        }
    }

}
