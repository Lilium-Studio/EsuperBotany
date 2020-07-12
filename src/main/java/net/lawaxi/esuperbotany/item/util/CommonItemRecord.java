package net.lawaxi.esuperbotany.item.util;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.item.ItemRecord;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CommonItemRecord extends ItemRecord {

    public CommonItemRecord(String name, SoundEvent soundIn) {
        super(name, soundIn);
        setUnlocalizedName("record");
        ForgeRegistries.ITEMS.register(this.setRegistryName(name));
        EsuCommons.items.add(this);
    }
}
