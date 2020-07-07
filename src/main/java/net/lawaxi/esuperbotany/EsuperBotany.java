package net.lawaxi.esuperbotany;

import net.lawaxi.esuperbotany.utils.Proxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = "esuperbotany", name = "Esuper Botany", version = "alfa4")
public class EsuperBotany
{
    public static EsuperBotany instance;

    @SidedProxy(serverSide = "net.lawaxi.esuperbotany.utils.Proxy",clientSide = "net.lawaxi.esuperbotany.utils.ClientProxy")
    public static Proxy proxy;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        instance = this;
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }


    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
