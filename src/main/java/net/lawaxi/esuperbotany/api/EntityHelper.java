package net.lawaxi.esuperbotany.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.common.core.helper.Vector3;

import java.util.ArrayList;
import java.util.List;

public class EntityHelper {


    public static final <T extends EntityLivingBase> T findTarget(EntityPlayer player, final Class<T> classs, int maxdis){


        Vector3 target = Vector3.fromEntityCenter(player);
        List<Entity> entities = new ArrayList();
        int distance = 1;


        while(entities.size() == 0 && distance < maxdis) {
            target = target.add((new Vector3(player.getLookVec())).multiply((double)distance)).add(0.0D, 0.5D, 0.0D);
            entities = player.world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(target.x - 3.0D, target.y - 3.0D, target.z - 3.0D, target.x + 3.0D, target.y + 3.0D, target.z + 3.0D));
            ++distance;

            for(Entity e : entities){

                if(classs == EntityLivingBase.class){
                    if(e instanceof EntityLivingBase)
                        return (T)e;

                }else if(classs == EntityPlayer.class){
                    if(e instanceof EntityPlayer)
                        return (T)e;
                }
            }
        }

        return null;
    }
}
