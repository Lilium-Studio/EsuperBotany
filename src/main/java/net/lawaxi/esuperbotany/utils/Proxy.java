package net.lawaxi.esuperbotany.utils;

import net.lawaxi.esuperbotany.common.world.WorldGenerator;
import net.lawaxi.esuperbotany.utils.register.*;
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
        EsuPetalRecipe.init();
        EsuLexicon.init();
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {
    }
}
