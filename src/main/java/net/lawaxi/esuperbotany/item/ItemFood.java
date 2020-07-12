package net.lawaxi.esuperbotany.item;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import javax.annotation.Nullable;
import java.util.List;

public class ItemFood extends net.minecraft.item.ItemFood {

    public static final String[] names = {"elvenBread"};
    public static int[] heal = {8};
    public static float[] saturation = {2};

    public ItemFood() {
        super(0,0,false);

        String name = "esuperbotany:food";
        setUnlocalizedName(name);
        setHasSubtypes(true);
        ForgeRegistries.ITEMS.register(this.setRegistryName(name));
        EsuCommons.items.add(this);
    }

    @Override
    public int getHealAmount(ItemStack stack) {
        return heal[stack.getMetadata()%heal.length];
    }


    @Override
    public float getSaturationModifier(ItemStack stack) {
        return saturation[stack.getMetadata()%saturation.length];
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item.esuperbotany:"+names[stack.getMetadata()%names.length];
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        switch (stack.getMetadata()){

            case 0:{
                tooltip.add(TextFormatting.ITALIC+I18n.format("item.esuperbotany:elvenBread.lore"));
                break;
            }

        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
