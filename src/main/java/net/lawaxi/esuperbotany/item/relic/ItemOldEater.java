package net.lawaxi.esuperbotany.item.relic;

import net.lawaxi.esuperbotany.api.Helper;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

public class ItemOldEater extends CommonItemRelic implements IManaUsingItem, IRelic {

    private static final int cost = 1998;
    private static final float per = 4.0F;

    public ItemOldEater() {
        super("esuperbotany:oldeaterrod",true,DamgeType.HURT);
    }

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {

        if(!playerIn.getEntityWorld().isRemote) {

            if (target.getHealth() <= per) {
                Helper.sendActionBar(playerIn, "info.oldeaterrod.failed1");
                return true;
            }

            if (ManaItemHandler.requestManaExactForTool(stack, playerIn, cost, false)) {
                Helper.sendActionBar(playerIn, "info.oldeaterrod.success");

                playerIn.swingArm(hand);
                target.attackEntityFrom(DamageSource.MAGIC, per);

                Entity a = new EntityItem(target.world, target.posX, target.posY + 2.0F, target.posZ, newMoney());
                ((EntityItem) a).setDefaultPickupDelay();
                target.world.spawnEntity(a);

            } else
                Helper.sendActionBar(playerIn, "info.oldeaterrod.failed2");

        }
        return true;
    }


    private ItemStack newMoney(){
        return getStack(moneys[itemRand.nextInt(moneys.length)]);
    }


    private static final String[] moneys = {"diamond","gold_ingot","iron_ingot","esuperbotany:copper_ingot","emerald","x"};
    private static final ItemStack getStack(String name){
        if(name.equalsIgnoreCase("x"))
            return new ItemStack(EsuCommons.LOOTBAG,1,0);
        else
            return new ItemStack(Item.getByNameOrId(name));

    }
}
