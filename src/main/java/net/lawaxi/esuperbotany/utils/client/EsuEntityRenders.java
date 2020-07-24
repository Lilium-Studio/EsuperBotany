package net.lawaxi.esuperbotany.utils.client;

import net.lawaxi.esuperbotany.client.renderer.entity.RenderHellAirBottle;
import net.lawaxi.esuperbotany.client.renderer.entity.RenderXTHand;
import net.lawaxi.esuperbotany.client.renderer.entity.boss.vazkii.RenderFlowerMissile;
import net.lawaxi.esuperbotany.client.renderer.entity.boss.vazkii.RenderVazkii;
import net.lawaxi.esuperbotany.entity.EntityHellAirBottle;
import net.lawaxi.esuperbotany.entity.EntityXTHand;
import net.lawaxi.esuperbotany.entity.boss.vazkii.EntityFlowerMissile;
import net.lawaxi.esuperbotany.entity.boss.vazkii.EntityVazkii;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EsuEntityRenders {

    public static void init(){


        RenderingRegistry.registerEntityRenderingHandler(EntityVazkii.class, manager -> new RenderVazkii(manager));
        RenderingRegistry.registerEntityRenderingHandler(EntityHellAirBottle.class, manager -> new RenderHellAirBottle(manager, Minecraft.getMinecraft().getRenderItem()));
        RenderingRegistry.registerEntityRenderingHandler(EntityXTHand.class, manager -> new RenderXTHand(manager));
        RenderingRegistry.registerEntityRenderingHandler(EntityFlowerMissile.class, manager -> new RenderFlowerMissile(manager, Minecraft.getMinecraft().getRenderItem()));

    }
}
