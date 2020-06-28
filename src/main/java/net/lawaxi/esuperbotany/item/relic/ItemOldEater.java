package net.lawaxi.esuperbotany.item.relic;

import net.lawaxi.esuperbotany.api.Helper;
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
        super("esuperbotany:oldeaterrod",true,1);
    }

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {

        if(target.getHealth()<=per){
            Helper.sendActionBar(playerIn, "info.oldeaterrod.failed1");
            return true;
        }

        if (ManaItemHandler.requestManaExactForTool(stack, playerIn, cost, false)) {
            Helper.sendActionBar(playerIn, "info.oldeaterrod.success");

            playerIn.swingArm(hand);
            target.attackEntityFrom(DamageSource.MAGIC,per);

            EntityItem a = new EntityItem(target.world);
            a.setLocationAndAngles(target.posX,target.posY+2.0F,target.posZ,0,0);
            a.setItem(newMoney());
            a.setPickupDelay(0);
            target.world.onEntityAdded(a);

        }else
            Helper.sendActionBar(playerIn, "info.oldeaterrod.failed2");

        return true;
    }

    private static final String[] moneys = {"diamond","gold_ingot","iron_ingot","esuperbotany:copper_ingot","emerald"};
    public static ItemStack newMoney(){
        return new ItemStack(Item.getByNameOrId(moneys[itemRand.nextInt(moneys.length)]));
    }
}
