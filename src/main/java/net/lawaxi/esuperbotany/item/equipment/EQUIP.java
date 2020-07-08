package net.lawaxi.esuperbotany.item.equipment;

import net.minecraft.inventory.EntityEquipmentSlot;

public enum EQUIP {
    Helmet, Chestplate, Leggings, Boots;


    public static final EntityEquipmentSlot switchh(EQUIP type)
    {
        switch(type)
        {
            case Helmet:
                return EntityEquipmentSlot.HEAD;
            case Chestplate:
                return EntityEquipmentSlot.CHEST;
            case Leggings:
                return EntityEquipmentSlot.LEGS;
            default:
                return EntityEquipmentSlot.FEET;
        }
    }
}
