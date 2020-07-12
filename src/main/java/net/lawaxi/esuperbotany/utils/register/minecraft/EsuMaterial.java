package net.lawaxi.esuperbotany.utils.register.minecraft;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class EsuMaterial {

    public static ItemArmor.ArmorMaterial ARMOR_STINKY;
    public static ItemArmor.ArmorMaterial ARMOR_XT;
    public static ItemArmor.ArmorMaterial BHSFUniform;

    public static void init(){

        //泰拉: 3,8,6,3;3

        ARMOR_XT = EnumHelper.addArmorMaterial("XT","xt",20, new int[]{4, 7, 8, 3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.5F);
        BHSFUniform = EnumHelper.addArmorMaterial("BHSF","bhsf",20, new int[]{0, 8, 0, 0}, 30, SoundEvents.ITEM_ARMOR_EQIIP_ELYTRA, 3.5F);

        //ARMOR_STINKY = EnumHelper.addArmorMaterial("STINKY","stinky",34, new int[]{3, 6, 8, 3}, 26, EsuSounds._114514_SHORT, 3F);
        //MANA = EnumHelper.addToolMaterial("BOTANIA", 5, 2500, 9.0F, 2.5F, 20);
    }
}
