package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.EsuperBotany;
import net.lawaxi.esuperbotany.common.entity.Vazkii.EntityVazkii;
import net.lawaxi.esuperbotany.renderer.entity.Vazkii.RendererVazkii;
import net.lawaxi.esuperbotany.utils.names.EntitiesNames;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EsuEntities {


    public static void init(){

        //渲染
        RenderingRegistry.registerEntityRenderingHandler(EntityVazkii.class, manager -> new RendererVazkii(manager));

        //实体
        register(EntitiesNames.VAZKII,EntityVazkii.class,256,3,true);

    }

    private static int id =0;
    public static void register(String name,Class<? extends Entity> classs,int a,int b,boolean c){
        EntityRegistry.registerModEntity(
                new ResourceLocation("esuperbotany", name),
                classs,
                "esuperbotany:"+name,
                id++,
                EsuperBotany.instance,
                a,b,c);
    }
}
