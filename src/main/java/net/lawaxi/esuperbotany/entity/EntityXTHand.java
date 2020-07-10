package net.lawaxi.esuperbotany.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityXTHand extends EntityThrowable {

    public EntityXTHand(World world, EntityPlayer player) {
        super(world,player);
        this.setSize(0.25F,0.25F);
    }

    public EntityXTHand(World worldIn) {
        super(worldIn);
        this.setSize(0.25F, 0.25F);
    }



    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }

    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && result.entityHit!=thrower)
        {
            result.entityHit.attackEntityFrom(DamageSource.MAGIC, 1F);
            result.entityHit.setFire(5);
            this.setDead();
        }
    }

    @Override
    public void onUpdate() {
        this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        super.onUpdate();

    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }


}
