package net.lawaxi.esuperbotany.entity;

import net.lawaxi.esuperbotany.api.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
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

    @Override
    protected void onImpact(RayTraceResult result) {

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

    private int delay = 20*5;

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();

        EntityHelper.particleSimple(world,new BlockPos(posX,posY,posZ), EnumParticleTypes.FLAME);

        delay--;
        if(delay==0)
            this.setDead();

        for (EntityLivingBase entity : world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(posX + 0.5, posY + 0.5, posZ + 0.5, posX + 0.5, posY - 0.5, posZ - 0.5))) {
            if (entity != thrower) {

                entity.attackEntityFrom(DamageSource.causeMobDamage(thrower), 5);
                entity.setFire(5);
                delay = Math.min(delay, 5);
            }
        }
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
