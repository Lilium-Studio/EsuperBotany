package net.lawaxi.esuperbotany.block.flora.generating;

import net.lawaxi.esuperbotany.utils.register.EsuLexicon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.boss.IBotaniaBoss;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileGenerating;

public class Lily extends SubTileGenerating {


    /*

        产能花：百合

        百合花会杀死踩在自己身上的除创造模式玩家,凋零,末影龙,植魔boss外全部生物并释放魔力
        当它被通上红石信号后,就会停止工作,像漏斗一样.

     */

    @Override
    public void onUpdate() {

        super.onUpdate();

        if(mana<getMaxMana() && redstoneSignal==0){

            for(EntityLivingBase entity :
                    supertile.getWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos()))){

                if(entity instanceof EntityPlayer){
                    if(((EntityPlayer) entity).isCreative()){
                        continue;
                    }
                }

                if(entity instanceof EntityDragon || entity instanceof EntityWither || entity instanceof IBotaniaBoss){
                    getWorld().createExplosion(null,getPos().getX(),getPos().getY(),getPos().getZ(),5F,true);
                    continue;
                }

                entity.onKillCommand();
                mana+=50;
            }

        }
    }

    @Override
    public int getDelayBetweenPassiveGeneration() {
        return 1;
    }

    @Override
    public int getMaxMana() {
        return 400;
    }

    @Override
    public LexiconEntry getEntry() {
        return EsuLexicon.LILY;
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }
}
