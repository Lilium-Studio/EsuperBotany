package net.lawaxi.esuperbotany.utils.register.minecraft;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class EsuSounds {
    public static SoundEvent _114514;
    public static SoundEvent BHSF;
    public static SoundEvent HISTORY_OF_MOON;

    public static void init(){
        _114514 = new SoundEvent(new ResourceLocation("esuperbotany", "114514"));
        BHSF = new SoundEvent(new ResourceLocation("esuperbotany", "bhsf"));
        HISTORY_OF_MOON = new SoundEvent(new ResourceLocation("esuperbotany", "history_of_moon"));

    }
}
