package net.lawaxi.esuperbotany.item.relic.util;

import net.lawaxi.esuperbotany.api.Helper;
import net.lawaxi.esuperbotany.item.relic.DamgeType;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.core.helper.ItemNBTHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class CommonItemBowRelic extends ItemBow implements IRelic {


    public CommonItemBowRelic(String name, boolean haslore, DamgeType damgeType) {
        super();
        setMaxStackSize(1);
        setUnlocalizedName(name);
        ForgeRegistries.ITEMS.register(this.setRegistryName(name));
        EsuCommons.items.add(this);

        this.hasLore = haslore;
        this.damgeType = damgeType;
    }

    private final boolean hasLore;
    private final DamgeType damgeType;

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

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
                entityIn.attackEntityFrom(new DamageSource("mana-relic"), 2);

            }else if(damgeType==DamgeType.DROP){

                //丢弃
                if(entityIn instanceof EntityPlayer){
                    ((EntityPlayer) entityIn).dropItem(true);
                }
            }
        }
    }

    //来自 ItemBow.class
    protected void setEnchant(ItemStack stack, EntityArrow entityarrow, boolean critical, boolean inf){

        if(critical)
            entityarrow.setIsCritical(true);

        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

        if (j > 0)
        {
            entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
        }

        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

        if (k > 0)
        {
            entityarrow.setKnockbackStrength(k);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
        {
            entityarrow.setFire(100);
        }

        if(inf)
            entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;

    }
}
