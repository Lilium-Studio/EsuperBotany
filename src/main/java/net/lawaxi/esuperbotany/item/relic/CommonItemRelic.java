package net.lawaxi.esuperbotany.item.relic;

import net.lawaxi.esuperbotany.item.CommonItem;
import net.lawaxi.esuperbotany.api.Helper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.core.helper.ItemNBTHelper;

import java.util.List;
import java.util.UUID;

public class CommonItemRelic extends CommonItem implements IRelic {

    public CommonItemRelic(String name, boolean hasLore, DamgeType damgeType) {

        super(name);
        this.hasLore = hasLore;
        this.damgeType = damgeType;
        setMaxStackSize(1);

    }

    private final boolean hasLore;
    private final DamgeType damgeType;


    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flags) {

        if(hasLore)
            tooltip.add(TextFormatting.ITALIC + I18n.format(getUnlocalizedName()+".lore"));

        //遗物信息
        if(GuiScreen.isShiftKeyDown()) {
            if(!hasUUID(stack)) {

                tooltip.add(Helper.colorSymbol(I18n.format("botaniamisc.relicUnbound")));
            } else {
                if(!getSoulbindUUID(stack).equals(Minecraft.getMinecraft().player.getUniqueID()))
                    tooltip.add(Helper.colorSymbol(I18n.format("botaniamisc.notYourSagittarius")));
                else tooltip.add(Helper.colorSymbol(I18n.format("botaniamisc.relicSoulbound", Minecraft.getMinecraft().player.getName())));
            }

        } else tooltip.add(Helper.colorSymbol(I18n.format("botaniamisc.shiftinfo")));
    }

    private static final String TAG ="soulbindUUID";

    @Override
    public boolean hasUUID(ItemStack itemStack) {
        return getSoulbindUUID(itemStack) != null;
    }

    @Override
    public void bindToUUID(UUID uuid, ItemStack stack) {
        ItemNBTHelper.setString(stack, TAG, uuid.toString());
    }

    @Override
    public UUID getSoulbindUUID(ItemStack stack) {
        if (ItemNBTHelper.verifyExistance(stack, TAG)) {
            try {
                return UUID.fromString(ItemNBTHelper.getString(stack, TAG, ""));
            } catch (IllegalArgumentException var3) {
                ItemNBTHelper.removeEntry(stack, TAG);
            }
        }

        return null;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);

        if(!hasUUID(stack)){
            bindToUUID(entityIn.getUniqueID(),stack);
        }else if(!getSoulbindUUID(stack).equals(entityIn.getUniqueID())){

            // 创造模式不受惩罚
            if(entityIn instanceof EntityPlayer){
                if(((EntityPlayer) entityIn).capabilities.isCreativeMode){
                    return;
                }
            }

            // 根据惩罚形式惩罚
            if(damgeType==DamgeType.HURT){
                //伤害
                entityIn.attackEntityFrom(new DamageSource("botania-relic"), 2);

            }else if(damgeType==DamgeType.DROP){

                //丢弃
                if(entityIn instanceof EntityPlayer){
                    ((EntityPlayer) entityIn).dropItem(true);
                }
            }
        }
    }
}
