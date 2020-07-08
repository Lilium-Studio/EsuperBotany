package net.lawaxi.esuperbotany.utils.register;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

public class EsuMaterial {

    public static ItemArmor.ArmorMaterial ARMOR_STINKY;
    public static ItemTool.ToolMaterial MANA;

    public static void init(){
        ARMOR_STINKY = EnumHelper.addArmorMaterial("STINKY","stinky",34, new int[]{3, 6, 8, 3}, 26, EsuSounds._114514_SHORT, 3F);
        //MANA = EnumHelper.addToolMaterial("BOTANIA", 5, 2500, 9.0F, 2.5F, 20);
    }
}
