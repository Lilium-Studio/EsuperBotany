package net.lawaxi.esuperbotany.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.Vector3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


    private static final Random rand = new Random();


    public static void particleAround(Entity entity, EnumParticleTypes type, boolean little)
    {

        for (int i = 0; i < 5; ++i)
        {
            double d0 = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            entity.world.spawnParticle(type, entity.posX + (double)(rand.nextFloat() * entity.width * 2.0F) - (double)entity.width, entity.posY + (little ? 0.0D : 1.0D) + (double)(rand.nextFloat() * entity.height), entity.posZ + (double)(rand.nextFloat() * entity.width * 2.0F) - (double)entity.width, d0, d1, d2);
        }
    }

    public static void particleSimple(World world, BlockPos pos, EnumParticleTypes type){
        world.spawnParticle(type, pos.getX(), pos.getY() + 0.5D, pos.getZ(), 0.0D, 0.0D, 0.0D);
    }
}
