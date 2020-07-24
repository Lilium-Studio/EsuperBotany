package net.lawaxi.esuperbotany.item.relic;

import net.lawaxi.esuperbotany.api.PlayerHelper;
import net.lawaxi.esuperbotany.item.util.CommonItemRelic;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import vazkii.botania.common.item.ModItems;

public class ItemDice extends CommonItemRelic {

    public ItemDice() {
        super("esuperbotany:dice", false, DamgeType.DROP);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        ItemStack me = playerIn.getHeldItem(handIn);

        if(me.isEmpty())
            return ActionResult.newResult(EnumActionResult.PASS,me);

        if(!getSoulbindUUID(me).equals(playerIn.getUniqueID()))
            return ActionResult.newResult(EnumActionResult.PASS,me);

        if (!worldIn.isRemote) {
            PlayerHelper.giveItem(playerIn,new ItemStack(ModItems.manaResource,10+itemRand.nextInt(10),5));
            PlayerHelper.giveItem(playerIn,new ItemStack(EsuCommons.LOOTBAG,10+itemRand.nextInt(10),2));

            me.shrink(1);

        } else
            playerIn.swingArm(handIn);

        return ActionResult.newResult(EnumActionResult.SUCCESS, me);
    }
}
