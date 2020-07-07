package net.lawaxi.esuperbotany.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import vazkii.botania.common.item.ModItems;

public class ItemLootBag extends CommonItem{


    public static final String[] names = {"resourceBag"};

    public ItemLootBag() {

        super("esuperbotany:lootbag");
        setHasSubtypes(true);
        setMaxStackSize(7);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item.esuperbotany:"+names[stack.getMetadata()%names.length];
    }

    private static final int[] resources = {0,1,2,3,4,5,6,7,8,9,11,12,13,14,15,16,17,18,19,21,22,23};

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {

        ItemStack me = player.getHeldItem(hand);
        switch (me.getMetadata()) {
            case 0: {

                if (me.isEmpty())
                    break;

                if (!worldIn.isRemote) {
                    me.shrink(1);
                    player.addItemStackToInventory(new ItemStack(ModItems.manaResource, 1, resources[itemRand.nextInt(resources.length)]));
                } else
                    player.swingArm(hand);


                return ActionResult.newResult(EnumActionResult.SUCCESS, me);
            }
        }
        return ActionResult.newResult(EnumActionResult.FAIL,me);
    }

}
