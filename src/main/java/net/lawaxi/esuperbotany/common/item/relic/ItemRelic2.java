package net.lawaxi.esuperbotany.common.item.relic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.core.helper.ItemNBTHelper;

import java.util.List;
import java.util.UUID;

public class ItemRelic2 extends Item implements IRelic {

    public ItemRelic2(String name, boolean hasLore, int damgeType) {

        this.hasLore = hasLore;
        this.damgeType = damgeType;

        setUnlocalizedName(name);
        setMaxStackSize(1);
        ForgeRegistries.ITEMS.register(this.setRegistryName(name));
        ModelLoader.setCustomModelResourceLocation(this,0,new ModelResourceLocation(name, "inventory"));
    }

    private final boolean hasLore;
    private final int damgeType;
    //0: 伤害 1:丢弃


    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flags) {

        if(hasLore)
            tooltip.add(TextFormatting.ITALIC + I18n.format(getUnlocalizedName()+".lore"));

        //遗物信息
        if(GuiScreen.isShiftKeyDown()) {
            if(!hasUUID(stack)) {
                tooltip.add(I18n.format("botaniamisc.relicUnbound").replace("&","§"));
            } else {
                if(!getSoulbindUUID(stack).equals(Minecraft.getMinecraft().player.getUniqueID()))
                    tooltip.add(I18n.format("botaniamisc.notYourSagittarius").replace("&","§"));
                else tooltip.add(I18n.format("botaniamisc.relicSoulbound", Minecraft.getMinecraft().player.getName()).replace("&","§"));
            }

        } else tooltip.add(I18n.format("botaniamisc.shiftinfo").replace("&","§"));
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
            if(damgeType==0){
                entityIn.attackEntityFrom(new DamageSource("botania-relic"), 2);
            }else if(damgeType==1){
                entityIn.entityDropItem(stack,1);
            }
        }
    }
}
