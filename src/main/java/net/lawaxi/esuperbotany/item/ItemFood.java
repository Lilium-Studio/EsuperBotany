package net.lawaxi.esuperbotany.item;

import net.lawaxi.esuperbotany.api.PlayerHelper;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

public class ItemFood extends net.minecraft.item.ItemFood {

    public static float P = 0.125F;

    public static final String[] names = {"elvenBread", "sleepingTea", "fractureDrink", "longjingTea"};
    public static int[] heal = {8, 2, 2, 4};
    public static float[] saturation = {6*P, 1*P, 2.5F*P, 3*P};
    public static int[] color = {0,0x8B4513,0xFFFFF0,0x3CB371};

    public ItemFood() {
        super(0, 0, false);

        String name = "esuperbotany:food";
        setUnlocalizedName(name);
        setHasSubtypes(true);
        ForgeRegistries.ITEMS.register(this.setRegistryName(name));
        EsuCommons.items.add(this);
    }

    @Override
    public int getHealAmount(ItemStack stack) {
        return heal[stack.getMetadata() % heal.length];
    }


    @Override
    public float getSaturationModifier(ItemStack stack) {
        return saturation[stack.getMetadata() % saturation.length];
    }


    public int getColor(ItemStack stack) {
        return color[stack.getMetadata()%color.length];
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item.esuperbotany:" + names[stack.getMetadata() % names.length];
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {

        boolean simple_player = false;
        if (entityLiving instanceof EntityPlayer) {
            simple_player = !((EntityPlayer) entityLiving).isCreative();
        }


        switch (stack.getMetadata()) {

            case 1:
            case 2:
            case 3:  {

                if (simple_player)
                    PlayerHelper.giveItem((EntityPlayer) entityLiving,new ItemStack(Items.GLASS_BOTTLE));

                break;
            }
        }

        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        switch (stack.getMetadata()) {

            case 0: {
                tooltip.add(TextFormatting.ITALIC + I18n.format("item.esuperbotany:elvenBread.lore"));
                break;
            }

        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }


    @SubscribeEvent
    public void rightClick(EntityItemPickupEvent e){
        if(e.getEntity().getEntityWorld().getBiome(e.getEntity().getPosition())== Biomes.SWAMPLAND &&
                e.getItem().getItem().getItem() == Items.POTIONITEM)
        {
            e.getItem().setItem(new ItemStack(EsuCommons.FOOD,e.getItem().getItem().getCount(),1));
        }
    }

}
