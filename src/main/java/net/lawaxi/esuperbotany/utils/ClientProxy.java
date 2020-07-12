package net.lawaxi.esuperbotany.utils;

import net.lawaxi.esuperbotany.client.renderer.entity.RenderHellAirBottle;
import net.lawaxi.esuperbotany.client.renderer.entity.RenderXTHand;
import net.lawaxi.esuperbotany.client.renderer.entity.Vazkii.RenderVazkii;
import net.lawaxi.esuperbotany.entity.EntityHellAirBottle;
import net.lawaxi.esuperbotany.entity.EntityXTHand;
import net.lawaxi.esuperbotany.entity.Vazkii.EntityVazkii;
import net.lawaxi.esuperbotany.item.ItemCosmetic;
import net.lawaxi.esuperbotany.item.ItemFood;
import net.lawaxi.esuperbotany.item.ItemLootBag;
import net.lawaxi.esuperbotany.item.ItemResource;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends Proxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {

        RenderingRegistry.registerEntityRenderingHandler(EntityVazkii.class, manager -> new RenderVazkii(manager));
        RenderingRegistry.registerEntityRenderingHandler(EntityHellAirBottle.class, manager -> new RenderHellAirBottle(manager, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityXTHand.class, manager -> new RenderXTHand(manager));


        super.preInit(event);

        for (Item a : EsuCommons.items) {
            if(!a.getHasSubtypes())
                ModelLoader.setCustomModelResourceLocation(a, 0, new ModelResourceLocation(a.getRegistryName(), "inventory"));
        }

        for(int i = 0; i< ItemResource.names.length; i++){
            ModelLoader.setCustomModelResourceLocation(EsuCommons.RESOURCE,i,new ModelResourceLocation("esuperbotany:"+ItemResource.names[i], "inventory"));
        }

        for(int i = 0; i< ItemCosmetic.names.length; i++){
            ModelLoader.setCustomModelResourceLocation(EsuCommons.COSMETIC,i,new ModelResourceLocation("esuperbotany:"+ItemCosmetic.names[i], "inventory"));
        }

        for(int i = 0; i< ItemLootBag.names.length; i++){
            ModelLoader.setCustomModelResourceLocation(EsuCommons.LOOTBAG,i,new ModelResourceLocation("esuperbotany:"+ItemLootBag.names[i], "inventory"));
        }

        for(int i = 0; i< ItemFood.names.length; i++){
            ModelLoader.setCustomModelResourceLocation(EsuCommons.FOOD,i,new ModelResourceLocation("esuperbotany:"+ItemFood.names[i], "inventory"));
        }
    }
}
