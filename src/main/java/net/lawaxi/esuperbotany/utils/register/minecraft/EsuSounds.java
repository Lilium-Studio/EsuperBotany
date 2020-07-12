package net.lawaxi.esuperbotany.utils.register.minecraft;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class EsuSounds {
    public static SoundEvent _114514;
    public static SoundEvent _114514_SHORT;
    public static SoundEvent BHSF;

    public static void init(){
        _114514 = new SoundEvent(new ResourceLocation("esuperbotany", "114514"));
        _114514_SHORT = new SoundEvent(new ResourceLocation("esuperbotany", "114514_short"));
        BHSF = new SoundEvent(new ResourceLocation("esuperbotany", "bhsf"));

    }
}
