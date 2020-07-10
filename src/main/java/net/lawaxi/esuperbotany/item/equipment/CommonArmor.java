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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.mana.ManaItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommonArmor extends ItemArmor {

    private static final HashMap<String, List<Item>> armors = new HashMap<>();
    private final String group;
    private final int repairCost;

    protected final HashMap<EntityEquipmentSlot,ModelBiped> models = new HashMap<>();

    public CommonArmor(@Nullable String group,@Nullable String name, ArmorMaterial material,EntityEquipmentSlot type, int repairCost) {

        super(material,material.ordinal(), type);

        this.group = group;
        this.repairCost = repairCost;

        String n;
        if(name==null){
            switch (type){
                case HEAD:
                    n = "esuperbotany:"+group+"Helmet";
                    break;
                case CHEST:
                    n = "esuperbotany:"+group+"Chestplate";
                    break;
                case LEGS:
                    n = "esuperbotany:"+group+"Leggings";
                    break;
                default:
                    n = "esuperbotany:"+group+"Boots";
                    break;
            }
        }else
            n = "esuperbotany:"+name;

        this.setUnlocalizedName(n);
        ForgeRegistries.ITEMS.register(this.setRegistryName(n));
        EsuCommons.items.add(this);

        if(group!=null) {
            if (armors.containsKey(group)) {
                if (armors.get(group) == null)
                    armors.replace(group, new ArrayList<>());
            } else
                armors.put(group, new ArrayList<>());
            armors.get(group).add(this);
        }

        this.setCreativeTab(null);
    }


    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        if(group==null)
            return;

        if(GuiScreen.isShiftKeyDown()){

            //getArmorSetTitle
            tooltip.add(Helper.colorSymbol(I18n.format("botaniamisc.armorset"))+" "+I18n.format("esuperbotany.armorset."+this.group+".name"));


            //addArmorSetDescription
            tooltip.add(I18n.format("esuperbotany.armorset."+this.group+".desc0"));
            tooltip.add(I18n.format("esuperbotany.armorset."+this.group+".desc1"));

            //hasArmorSetItem
            for(Item armor : armors.get(group)){
                tooltip.add(" - "+I18n.format("item."+armor.getUnlocalizedName()+".name"));
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

        if(group==null)
            return false;

        NonNullList<ItemStack> a = player.inventory.armorInventory;

        s:
        for(Item armor: this.armors.get(group)){

            for(ItemStack stack:a)
            {
                if(stack.getItem() == armor)
                    continue s;
            }

            return false;
        }

        return true;
    }
}
