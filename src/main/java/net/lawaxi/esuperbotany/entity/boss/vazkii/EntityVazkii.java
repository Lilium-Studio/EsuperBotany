package net.lawaxi.esuperbotany.entity.boss.vazkii;

import net.lawaxi.esuperbotany.entity.boss.BossCheckingHelper;
import net.lawaxi.esuperbotany.entity.boss.util.CommonBoss;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import vazkii.botania.api.boss.IBotaniaBoss;

public class EntityVazkii extends CommonBoss implements IBotaniaBoss, IEntityAdditionalSpawnData {

    public EntityVazkii(World world) {
        super(world,"vazkii");
        teleport_normal = 50;
        teleport_damged = 10;
        setHealth(700.0F);
    }

    public EntityVazkii(World world, BlockPos source) {
        super(world,source,"vazkii");
        teleport_normal = 50;
        teleport_damged = 10;
        setHealth(700.0F);
    }

    public static boolean spawn(EntityPlayer player, ItemStack stack, World world, BlockPos pos) {
        if(BossCheckingHelper.checkSpawn(player,stack,world,pos)){

            if (!world.isRemote) {
                stack.shrink(1);
                EntityVazkii e = new EntityVazkii(world,pos);
                e.setPosition((double) pos.getX() + 0.5D, (double) (pos.getY() + 3), (double) pos.getZ() + 0.5D);

                e.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double) (320.0F * (float) e.playerCount));
                e.playSound(SoundEvents.ENTITY_ENDERDRAGON_GROWL, 10F, 0.1F);
                e.onInitialSpawn(world.getDifficultyForLocation(new BlockPos(e)), (IEntityLivingData) null);
                world.spawnEntity(e);
            }

            return true;
        }

        return false;
    }


    private static final int delayOfFlowerShot = 3*20;
    private int Delay1 = delayOfFlowerShot;

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if(world.isRemote)
            return;

        if(isDead)
            return;

        //发射花朵
        if(Delay1>0){
            Delay1--;
            if(Delay1==0){

                shootFlower();
                Delay1=delayOfFlowerShot;
            }
        }

        //限定最大血量
        for(EntityPlayer player:getPlayersAround())
            player.setHealth(Math.min(player.getHealth(),20.0F));
    }


    private void shootFlower() {

        EntityFlowerMissile missile = new EntityFlowerMissile(this);
        missile.setPosition(posX + (Math.random() - 0.5 * 0.1), posY + 2.4 + (Math.random() - 0.5 * 0.1), posZ + (Math.random() - 0.5 * 0.1));

        if (missile.findTarget()) {
            playSound(vazkii.botania.common.core.handler.ModSounds.missile, 0.6F, 0.8F + (float) Math.random() * 0.2F);
            world.spawnEntity(missile);
        }

    }
}
