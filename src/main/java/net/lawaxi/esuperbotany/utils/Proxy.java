package net.lawaxi.esuperbotany.utils;

import net.lawaxi.esuperbotany.utils.register.*;
import net.lawaxi.esuperbotany.utils.register.botania.EsuBotaniaAll;
import net.lawaxi.esuperbotany.utils.register.botania.EsuSubTiles;
import net.lawaxi.esuperbotany.world.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Proxy {

    public void preInit(FMLPreInitializationEvent event) {

        GameRegistry.registerWorldGenerator(new WorldGenerator(), 3);

        EsuSubTiles.init();
        EsuSounds.init();
        EsuMaterial.init();
        EsuCommons.init();
        EsuCreativeTab.init();
        EsuEntities.init();
    }

    public void init(FMLInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(new EsuEvents());
    }

    public void postInit(FMLPostInitializationEvent event) {

        EsuBotaniaAll.init();
    }
}
