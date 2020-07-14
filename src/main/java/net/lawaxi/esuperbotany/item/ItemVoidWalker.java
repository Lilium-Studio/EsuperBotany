package net.lawaxi.esuperbotany.item;

import net.lawaxi.esuperbotany.item.util.CommonItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemVoidWalker extends CommonItem implements IManaUsingItem {

    public ItemVoidWalker() {
        super("esuperbotany:voidwalker");
    }

    private static final int cost_on_binding = 100;
    private static final int cost_per_tick = 1;

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if(entityIn instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) entityIn;

            if(ItemNBTHelper.verifyExistance(stack,"bind")){

                if(onHand(stack,player)) {
                    if(costManaPerTick(stack,player))
                        player.motionY = 0;
                }
                else
                    ItemNBTHelper.removeEntry(stack,"bind");

            }else{

                if(onHand(stack,player) && costManaOnBinding(stack,player)) {
                    ItemNBTHelper.setBoolean(stack,"bind",true);
                    player.swingArm(((EntityPlayer) entityIn).getHeldItemMainhand() == stack ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
                }

            }
        }

        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return ItemNBTHelper.getInt(itemStack,"cost",1)!=0;
    }

    private boolean costManaPerTick(ItemStack stack,EntityPlayer player){

        if(!usesMana(stack))
            return true;

        return ManaItemHandler.requestManaExactForTool(stack, player, cost_per_tick, true);
    }

    private boolean costManaOnBinding(ItemStack stack,EntityPlayer player){

        if(!usesMana(stack))
            return true;

        return ManaItemHandler.requestManaExactForTool(stack, player, cost_on_binding, true);
    }


}
