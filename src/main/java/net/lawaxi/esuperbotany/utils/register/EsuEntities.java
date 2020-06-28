package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.EsuperBotany;
import net.lawaxi.esuperbotany.client.renderer.entity.Vazkii.RenderVazkii;
import net.lawaxi.esuperbotany.entity.Vazkii.EntityVazkii;
import net.lawaxi.esuperbotany.utils.names.EntitiesNames;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EsuEntities {


    public static void init(){

        //渲染
        RenderingRegistry.registerEntityRenderingHandler(EntityVazkii.class, manager -> new RenderVazkii(manager));

        //实体
        register(EntitiesNames.VAZKII,EntityVazkii.class,256,3,true);
        register(EntitiesNames.VAZKII,EntityVazkii.class,64,3,true);
    }

    private static int id =0;
    public static void register(String name,Class<? extends Entity> classs,int a,int b,boolean c){
        EntityRegistry.registerModEntity(
                new ResourceLocation("esuperbotany", name),
                classs, "esuperbotany:"+name, id++, EsuperBotany.instance, a,b,c);
    }
}
