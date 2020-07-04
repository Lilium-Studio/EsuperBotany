package net.lawaxi.esuperbotany.item.equipment;

import net.lawaxi.esuperbotany.utils.register.EsuSounds;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class ArmorStinky extends CommonArmor {

    private static final ItemArmor.ArmorMaterial MATERIAL = EnumHelper.addArmorMaterial("stinky","esuperbotany:stinky",10, new int[]{2, 5, 7, 2}, 8, EsuSounds._114514_SHORT, 2F);

    public static final void build(){

        for(int i=0;i<4;i++){
            new ArmorStinky("stinky",MATERIAL,i);
        }
    }

    public ArmorStinky(String n, ArmorMaterial material, int type) {
        super(n, material, type);
    }
}