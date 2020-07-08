package net.lawaxi.esuperbotany.item;

import net.lawaxi.esuperbotany.api.Helper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

import javax.annotation.Nullable;
import java.util.List;

public class ItemLootBag extends CommonItem{


    public static final String[] names = {"resourceBag","flowerBag","runeBag"};

    public ItemLootBag() {

        super("esuperbotany:lootbag");
        setHasSubtypes(true);
        setMaxStackSize(49);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item.esuperbotany:"+names[stack.getMetadata()%names.length];
    }

    private static final int[] resources = {0,1,2,3,4,5,6,7,8,9,11,12,13,14,15,16,17,18,19,21,22,23};
    private static final int[] flowers = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    private static final int[] runes = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {

        ItemStack me = player.getHeldItem(hand);

        if(me.isEmpty())
            return ActionResult.newResult(EnumActionResult.PASS,me);

        switch (me.getMetadata()) {
            default: {

                if (!worldIn.isRemote) {
                    me.shrink(1);
                    player.addItemStackToInventory(new ItemStack(ModItems.manaResource, 1, resources[itemRand.nextInt(resources.length)]));
                } else
                    player.swingArm(hand);

            }
            case 1:{

                if(!worldIn.isRemote){
                    me.shrink(1);
                    player.addItemStackToInventory(new ItemStack(ModBlocks.flower,1,flowers[itemRand.nextInt(flowers.length)]));
                }else
                    player.swingArm(hand);
            }
            case 2:{
                if(!worldIn.isRemote){
                    me.shrink(1);
                    player.addItemStackToInventory(new ItemStack(ModItems.rune,1,runes[itemRand.nextInt(runes.length)]));
                }else
                    player.swingArm(hand);
            }
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, me);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        if(!GuiScreen.isShiftKeyDown()){
            tooltip.add(Helper.colorSymbol(I18n.format("botaniamisc.shiftinfo")));
        }
        else {
            tooltip.add(I18n.format("esupermisc.baginfo1"));

            switch (stack.getMetadata()) {
                default: {

                    for(int i : resources){
                        addInfo(tooltip,new ItemStack(ModItems.manaResource,1,i));
                    }
                    break;

                }
                case 1:{

                    for(int i : flowers){
                        addInfo(tooltip,new ItemStack(ModItems.flowerBag,1,i));
                    }
                    break;
                }
                case 2:{
                    for(int i : runes){
                        addInfo(tooltip,new ItemStack(ModItems.rune,1,i));
                    }
                    break;
                }
            }
        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    private void addInfo(List<String> tooltip,ItemStack stack){
        tooltip.add(I18n.format("esupermisc.baginfo2")+stack.getDisplayName());
    }
}
