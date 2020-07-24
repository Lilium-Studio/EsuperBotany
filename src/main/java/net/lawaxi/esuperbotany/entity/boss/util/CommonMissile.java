package net.lawaxi.esuperbotany.entity.boss.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.Vector3;

import javax.annotation.Nullable;
import java.util.List;

public abstract class CommonMissile extends EntityThrowable {

    public CommonMissile(World worldIn) {
        super(worldIn);
    }

    public CommonMissile(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    protected int time = 0;
    double lockX, lockY = -1, lockZ;

    @Override
    public void onUpdate() {
        double lastTickPosX = this.lastTickPosX;
        double lastTickPosY = this.lastTickPosY;
        double lastTickPosZ = this.lastTickPosZ;
        super.onUpdate();

        if(!world.isRemote && (!findTarget() || time > 40)) {
            setDead();
            return;
        }

        time++;


        EntityLivingBase target = getTargetEntity();
        if(target != null) {
            if (lockY == -1) {
                lockX = target.posX;
                lockY = target.posY;
                lockZ = target.posZ;
            }

            Vector3 targetVec = new Vector3(lockX, lockY, lockZ);
            Vector3 diffVec = targetVec.subtract(new Vector3(posX, posY, posZ));
            Vector3 motionVec = diffVec.normalize().multiply(0.5);
            motionX = motionVec.x;
            motionY = motionVec.y;
            if (time < 10)
                motionY = Math.abs(motionY);
            motionZ = motionVec.z;
        }
    }

    public boolean findTarget() {
        EntityLivingBase target = getTargetEntity();
        if(target != null && target.getHealth() > 0 && !target.isDead && world.loadedEntityList.contains(target))
            return true;

        if(target != null)
            setTarget(null);

        double range = 12;
        List<EntityPlayer> entities = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(posX - range, posY - range, posZ - range, posX + range, posY + range, posZ + range));

        for(EntityPlayer player:entities){
            if(!player.isDead){
                setTarget(player);
                break;
            }

        }

        return getTargetEntity()!=null;
    }

    public void setTarget(EntityLivingBase e) {
        dataManager.set(TARGET, e == null ? -1 : e.getEntityId());
    }

    @Nullable
    public EntityLivingBase getTargetEntity() {
        int id = dataManager.get(TARGET);
        Entity e = world.getEntityByID(id);
        if(e != null && e instanceof EntityLivingBase)
            return (EntityLivingBase) e;

        return null;
    }

    private static final DataParameter<Integer> TARGET = EntityDataManager.createKey(CommonMissile.class, DataSerializers.VARINT);

    @Override
    protected void entityInit() {
        this.dataManager.register(TARGET, 0);
    }
}
