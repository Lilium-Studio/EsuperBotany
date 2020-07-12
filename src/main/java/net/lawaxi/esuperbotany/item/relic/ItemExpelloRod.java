package net.lawaxi.esuperbotany.item.relic;

import net.lawaxi.esuperbotany.api.EntityHelper;
import net.lawaxi.esuperbotany.item.util.CommonItemRelic;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

public class ItemExpelloRod extends CommonItemRelic implements IManaUsingItem {

    private static final int cost = 5000;

    public ItemExpelloRod() {
        super("esuperbotany:expellorod",false,DamgeType.HURT);
    }

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {

        ItemStack me = player.getHeldItem(hand);
        if(me.isEmpty()){
            return ActionResult.newResult(EnumActionResult.PASS,me);
        }

        EntityPlayer target = EntityHelper.findTarget(player,EntityPlayer.class,10);
        if(target!=null){

            if(!target.getHeldItemMainhand().isEmpty()) {

                if (ManaItemHandler.requestManaExactForTool(me, player, cost, true)) {

                    target.dropItem(true);

                    if(worldIn.isRemote)
                        player.swingArm(hand);

                    player.getCooldownTracker().setCooldown(this,20*5);

                    return ActionResult.newResult(EnumActionResult.SUCCESS,me);
                }
            }
        }

        return ActionResult.newResult(EnumActionResult.FAIL,me);
    }
}
