package net.lawaxi.esuperbotany.utils;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.lawaxi.esuperbotany.utils.register.EsuCreativeTab;
import net.lawaxi.esuperbotany.utils.register.EsuEntities;
import net.lawaxi.esuperbotany.utils.register.EsuFlora;
import net.lawaxi.esuperbotany.utils.register.botania.EsuBotaniaAll;
import net.lawaxi.esuperbotany.world.WorldGenerator;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Proxy {

    public void preInit(FMLPreInitializationEvent event) {

        GameRegistry.registerWorldGenerator(new WorldGenerator(), 3);

        EsuFlora.init();
        EsuCommons.init();
        EsuCreativeTab.init();
        EsuEntities.init();
    }

    public void init(FMLInitializationEvent event) {

        EsuBotaniaAll.init();
    }

    public void postInit(FMLPostInitializationEvent event) {
    }
}
