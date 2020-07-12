package net.lawaxi.esuperbotany.item.equipment;

import net.lawaxi.esuperbotany.utils.register.minecraft.EsuMaterial;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ArmorStinky extends CommonArmor {

    public ArmorStinky(EntityEquipmentSlot type) {
        super("stinky",null, EsuMaterial.ARMOR_STINKY, type,85);
    }
}