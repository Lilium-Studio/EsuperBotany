package net.lawaxi.esuperbotany.common.item.relic;

import net.lawaxi.esuperbotany.utils.Helper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

public class Item1srod extends ItemRelic2 implements IManaUsingItem, IRelic {

    private static final int cost = 1926;
    private static final float per = 2.0F;

    public Item1srod() {
        super("esuperbotany:+1srod",true,2);
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

                Helper.sendActionBar(playerIn,"info.+1srod.success1");
                return true;
            }

            Helper.sendActionBar(playerIn,"info.+1srod.failed3");
        }else{

            Helper.sendActionBar(playerIn,"info.+1srod.failed1");
        }
        return false;
    }

    @SubscribeEvent
    public void rightClick(PlayerInteractEvent.RightClickEmpty e) {

        if(e.getItemStack().getItem() == this && e.getEntityPlayer().shouldHeal()) {

            if (ManaItemHandler.requestManaExactForTool(e.getItemStack(), e.getEntityPlayer(), cost, false)) {
                e.getEntityPlayer().swingArm(e.getHand());
                e.getEntityPlayer().heal(per);

                Helper.sendActionBar(e.getEntityPlayer(),"info.+1srod.success2");

            }else{

                Helper.sendActionBar(e.getEntityPlayer(),"info.+1srod.failed3");
            }
        }else{

            Helper.sendActionBar(e.getEntityPlayer(),"info.+1srod.failed2");
        }


    }
}
