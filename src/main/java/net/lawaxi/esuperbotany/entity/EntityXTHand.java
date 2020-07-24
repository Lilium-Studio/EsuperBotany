package net.lawaxi.esuperbotany.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

import javax.annotation.Nullable;

public class EntityXTHand extends EntityThrowable implements IThrowableEntity {

    public EntityXTHand(World world, EntityPlayer player) {
        super(world,player);
        this.setSize(0.25F,0.25F);
    }

    public EntityXTHand(World worldIn) {
        super(worldIn);
        this.setSize(0.25F, 0.25F);
    }

    private int delay = 20*5;

    @Override
    protected void onImpact(RayTraceResult result) {
        if(result.entityHit!=null && result.entityHit!=thrower){

            result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(thrower), 5);
            result.entityHit.setFire(5);
            delay = Math.min(delay, 5);
        }

    }

    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    @Nullable
    @Override
    public EntityLivingBase getThrower() {
        return thrower;
    }

    @Override
    public void setThrower(Entity entity) {

        if(entity instanceof EntityLivingBase)
            thrower = (EntityLivingBase) entity;
    }
}
