package net.lawaxi.esuperbotany.item.relic;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.Vector3;

import java.util.ArrayList;
import java.util.List;

public class ItemExpelloRod extends CommonItemRelic implements IManaUsingItem {

    private static final int cost = 5000;

    public ItemExpelloRod() {
        super("esuperbotany:expellorod",false,DamgeType.DROP);
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

        EntityPlayer target = getTarget(player);
        if(target!=null){

            if(!target.getHeldItemMainhand().isEmpty()) {

                if (ManaItemHandler.requestManaExactForTool(me, player, cost, false)) {

                    target.dropItem(true);

                    if(worldIn.isRemote)
                        player.swingArm(hand);

                    return ActionResult.newResult(EnumActionResult.SUCCESS,me);
                }
            }
        }

        return ActionResult.newResult(EnumActionResult.FAIL,me);
    }


    //改自黑山法杖代码
    private EntityPlayer getTarget(EntityPlayer player){


        Vector3 target = Vector3.fromEntityCenter(player);
        List<Entity> entities = new ArrayList();
        int distance = 1;


        while(entities.size() == 0 && distance < 10) {
            target = target.add((new Vector3(player.getLookVec())).multiply((double)distance)).add(0.0D, 0.5D, 0.0D);
            entities = player.world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(target.x - 3.0D, target.y - 3.0D, target.z - 3.0D, target.x + 3.0D, target.y + 3.0D, target.z + 3.0D));
            ++distance;

            for(Entity e : entities){
                if(e instanceof EntityPlayer){
                    return (EntityPlayer)e;
                }
            }
        }

        return null;
    }
}
