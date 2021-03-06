package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.EsuperBotany;
import net.lawaxi.esuperbotany.entity.EntityHellAirBottle;
import net.lawaxi.esuperbotany.entity.EntityXTHand;
import net.lawaxi.esuperbotany.entity.boss.vazkii.EntityFlowerMissile;
import net.lawaxi.esuperbotany.entity.boss.vazkii.EntityVazkii;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EsuEntities {


    public static void init(){
        register("vazkii",EntityVazkii.class,256,3,true);
        register("hell_air_bottle", EntityHellAirBottle.class,64,10,true);
        register("xthand", EntityXTHand.class,64,10,true);
        register("vazkii_flowermissile", EntityFlowerMissile.class,64,10,true);
    }

    private static int id =0;
    private static void register(String name,Class<? extends Entity> classs,int a,int b,boolean c){
        EntityRegistry.registerModEntity(
                new ResourceLocation("esuperbotany", name),
                classs, "esuperbotany:"+name, id++, EsuperBotany.instance, a,b,c);
    }

}
