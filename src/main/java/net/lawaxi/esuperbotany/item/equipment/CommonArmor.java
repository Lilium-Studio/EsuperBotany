package net.lawaxi.esuperbotany.item.equipment;

import net.lawaxi.esuperbotany.api.Helper;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import vazkii.botania.api.mana.ManaItemHandler;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class CommonArmor extends ItemArmor {

    private final String name;
    private final int repairCost;

    protected final HashMap<EntityEquipmentSlot,ModelBiped> models = new HashMap<>();

    public CommonArmor(String n, ArmorMaterial material,EQUIP type, int repairCost) {

        super(material,material.ordinal(), EQUIP.switchh(type));

        this.name = n;
        this.repairCost = repairCost;

        String name = "esuperbotany:"+n+type.name();

        this.setUnlocalizedName(name);
        ForgeRegistries.ITEMS.register(this.setRegistryName(name));
        EsuCommons.items.add(this);
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(GuiScreen.isShiftKeyDown()){

            //getArmorSetTitle
            tooltip.add(Helper.colorSymbol(I18n.format("botaniamisc.armorset"))+" "+I18n.format("esuperbotany.armorset."+this.name+".name"));


            //addArmorSetDescription
            tooltip.add(I18n.format("esuperbotany.armorset."+this.name+".desc0"));
            tooltip.add(I18n.format("esuperbotany.armorset."+this.name+".desc1"));

            //hasArmorSetItem
            for(EQUIP equip : EQUIP.values()){

                String a = "esuperbotany:"+this.name+equip.name();

                if(Item.getByNameOrId(a)!=null) {
                    tooltip.add(" - "+I18n.format("item."+a+".name"));
                }
            }

        }else{
            tooltip.add(Helper.colorSymbol(I18n.format("botaniamisc.shiftinfo")));
        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {


        if(!world.isRemote && itemStack.getItemDamage()>=1 && ManaItemHandler.requestManaExactForTool(itemStack,player,repairCost,true))
        {
            itemStack.setItemDamage(itemStack.getItemDamage()-1);
        }
            super.onArmorTick(world, player, itemStack);
    }

    public boolean wearAll(EntityPlayer player){

        NonNullList<ItemStack> armors = player.inventory.armorInventory;

        if(armors.size()<4)
            return false;

        for(ItemStack stack:armors){
            if(stack.getItem().getRegistryName().toString().indexOf(name)==-1)
                return false;
        }

        return true;
    }
}
