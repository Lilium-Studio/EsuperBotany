package net.lawaxi.esuperbotany.item.equipment;

import net.lawaxi.esuperbotany.api.Helper;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import vazkii.botania.api.mana.IManaUsingItem;
import javax.annotation.Nullable;
import java.util.List;

public class CommonArmor extends ItemArmor implements IManaUsingItem {

    private static String name;

    public CommonArmor(String n, ArmorMaterial material,int type) {

        super(material,material.ordinal(), EQUIPMENT_SLOT(type));

        this.name = n;
        String name = "esuperbotany:"+n+EQUIPMENT[type];
        this.setUnlocalizedName(name);
        ForgeRegistries.ITEMS.register(this.setRegistryName(name));

        EsuCommons.items.add(this);
    }

    public static final String[] EQUIPMENT = {"Helmet","Chestplate","Leggings","Boots"};

    public static EntityEquipmentSlot EQUIPMENT_SLOT(int type)
    {
        switch(type)
        {
            case 0:
                return EntityEquipmentSlot.HEAD;
            case 1:
                return EntityEquipmentSlot.CHEST;
            case 2:
                return EntityEquipmentSlot.LEGS;
            default:
                return EntityEquipmentSlot.FEET;
        }
    }


    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }



    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(GuiScreen.isShiftKeyDown()){

            EntityPlayer player = Minecraft.getMinecraft().player;

            //getArmorSetTitle
            tooltip.add(Helper.colorSymbol(I18n.format("botaniamisc.armorset"))+" "+I18n.format("esuperbotany.armorset."+this.name+".name"));


            //addArmorSetDescription
            for(int i=0;true;i++){

                if(!I18n.hasKey("esuperbotany.armorset."+this.name+".dec"+i))
                    break;

                tooltip.add(I18n.format("esuperbotany.armorset."+this.name+".dec"+i));
            }

            //hasArmorSetItem
            for(int i=0;i<4;i++){

                String a = "esuperbotany:"+this.name+EQUIPMENT[i];

                if(Item.getByNameOrId(a)!=null) {
                    tooltip.add(" - "+I18n.format("item."+a+".name"));
                }
            }

        }else{
            tooltip.add(Helper.colorSymbol(I18n.format("botaniamisc.shiftinfo")));
        }
    }

}
