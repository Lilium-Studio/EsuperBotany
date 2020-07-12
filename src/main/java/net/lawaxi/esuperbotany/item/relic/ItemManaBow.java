package net.lawaxi.esuperbotany.item.relic;

import net.lawaxi.esuperbotany.item.util.CommonItemBowRelic;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemManaBow extends CommonItemBowRelic implements IManaUsingItem {


    public ItemManaBow() {
        super("esuperbotany:manabow", false, DamgeType.HURT);
    }

    private static final int cost = 5000;


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack me = playerIn.getHeldItem(handIn);


        boolean flag = false;

        if(playerIn.capabilities.isCreativeMode)
            flag = true;
        else {

            int count = 0;
            SHIT:
            for (int i = 0; i < playerIn.inventory.getSizeInventory(); ++i) {
                ItemStack mayArrow = playerIn.inventory.getStackInSlot(i);

                if (mayArrow.getItem() instanceof ItemArrow) {
                    if (mayArrow.isEmpty())
                        continue;

                    if (EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, me) > 0) {
                        flag = true;
                        break SHIT;
                    }

                    if (mayArrow.getCount() + count >= 3) {
                        flag = true;
                        break SHIT;
                    }

                    count += mayArrow.getCount();
                }
            }
        }

        if(!ManaItemHandler.requestManaExactForTool(me, playerIn, cost, false))
            flag = false;

        if(flag){
            playerIn.setActiveHand(handIn);
            return new ActionResult(EnumActionResult.SUCCESS, me);

        }else
            return new ActionResult(EnumActionResult.FAIL, me);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack me, World worldIn, EntityLivingBase entityLiving, int timeLeft) {

        if(!(entityLiving instanceof EntityPlayer))
            return;

        EntityPlayer player = (EntityPlayer)entityLiving;
        boolean inf = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, me) > 0;
        boolean creative = player.isCreative();

        ItemArrow a1 = null;
        ItemArrow a2 = null;
        ItemArrow a3 = null;
        ItemStack i1 = null;
        ItemStack i2 = null;
        ItemStack i3 = null;

        //从背包编号搜索前三根可用箭
        SHIT:
        for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
            ItemStack mayArrow = player.inventory.getStackInSlot(i);

            if(!mayArrow.isEmpty() && mayArrow.getItem() instanceof ItemArrow){

                int use = 0;

                while (mayArrow.getCount() - use >0){

                    if(a1==null) {
                        a1 = (ItemArrow) mayArrow.getItem();
                        i1 = mayArrow;
                    }
                    else if(a2==null) {
                        a2 = (ItemArrow) mayArrow.getItem();
                        i2 = mayArrow;
                    }
                    else if(a3==null) {
                        a3 = (ItemArrow) mayArrow.getItem();
                        i3 = mayArrow;
                    }
                    else
                        break SHIT;

                    use++;
                }

            }

        }

        //创造模式不需要箭发射
        if(creative){
            inf = true;
            if(a1==null){

                a1 = new ItemArrow();
                i1 = new ItemStack(Items.ARROW);
            }
        }


        //有无限且只有一根箭时,将第一根箭复制
        if(a1!=null && (a2==null || a3==null)){
            if(inf) {
                if(a2==null) {
                    a2 = a1;
                    i2 = i1;
                }

                if(a3==null) {
                    a3 = a1;
                    i3 = i1;
                }

            }
        }

        int i = this.getMaxItemUseDuration(me) - timeLeft;
        float f = ItemBow.getArrowVelocity(i);

        ManaItemHandler.requestManaExactForTool(me, player, cost, true);

        if(!worldIn.isRemote) {

            //数据来自暮色森林三发弓

            if (a1 != null && i1 != null) {
                EntityArrow e1 = a1.createArrow(worldIn, i1, player);
                e1.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, f * 3.0F, 1.0F);
                setEnchant(me,e1,f==1.0F,inf);
                worldIn.spawnEntity(e1);
            }
            if (a2 != null && i2 != null) {
                EntityArrow e2 = a2.createArrow(worldIn, i2, player);
                e2.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, f * 2.0F, 1.0F);
                e2.motionY += 0.14999999664723873D;
                e2.posY += 0.02500000037252903D;
                setEnchant(me,e2,f==1.0F,inf);
                worldIn.spawnEntity(e2);
            }
            if (a3 != null && i3 != null) {
                EntityArrow e3 = a3.createArrow(worldIn, i3, player);
                e3.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, f * 2.0F, 1.0F);
                e3.motionY -= 0.14999999664723873D;
                e3.posY -= 0.02500000037252903D;
                setEnchant(me,e3,f==1.0F,inf);
                worldIn.spawnEntity(e3);
            }
        }


        if(!inf) {

            try {
                i1.shrink(1);
                i2.shrink(1);
                i3.shrink(1);

            } catch (NullPointerException e) {

            }
        }

        worldIn.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
    }

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return ItemNBTHelper.getInt(itemStack,"cost",1)!=0;
    }
}
