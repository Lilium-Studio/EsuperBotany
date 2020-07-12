package net.lawaxi.esuperbotany.utils.register.minecraft;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EsuSmelting {

    public static void init(){
        GameRegistry.addSmelting(new ItemStack(EsuCommons.COPPERORE), new ItemStack(EsuCommons.COPPERINGOT), 2);
    }
}
