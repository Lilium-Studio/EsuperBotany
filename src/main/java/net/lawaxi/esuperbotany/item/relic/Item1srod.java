package net.lawaxi.esuperbotany.item.relic;

import net.lawaxi.esuperbotany.api.Helper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
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
                spawnParticles(target);
                Helper.sendActionBar(playerIn,"info.+1srod.success1");
            }else {

                Helper.sendActionBar(playerIn, "info.+1srod.failed3");
            }
        }else{

            Helper.sendActionBar(playerIn,"info.+1srod.failed1");
        }

        //此处return ture就不会触发右击空气的事件
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        if (playerIn.getHealth() < playerIn.getMaxHealth()){

            if (ManaItemHandler.requestManaExactForTool(playerIn.getHeldItem(handIn), playerIn, cost, false)) {
                playerIn.swingArm(handIn);
                playerIn.heal(per);
                spawnParticles(playerIn);
                Helper.sendActionBar(playerIn, "info.+1srod.success2");

            } else {

                Helper.sendActionBar(playerIn, "info.+1srod.failed3");
            }
        }else{

            Helper.sendActionBar(playerIn, "info.+1srod.failed2");
        }
        
        return super.onItemRightClick(worldIn, playerIn, handIn);
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
