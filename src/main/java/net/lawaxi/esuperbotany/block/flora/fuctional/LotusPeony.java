package net.lawaxi.esuperbotany.block.flora.fuctional;

import net.lawaxi.esuperbotany.utils.register.botania.EsuLexicon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;

public class LotusPeony extends SubTileFunctional {

    /*

        功能花：荷包牡丹

        7x7范围内玩家获得生命提升5效果10秒
        已存在生命提升效果时不会重复或叠加
        每次消耗20魔力

     */


    @Override
    public void onUpdate() {
        super.onUpdate();

        for(EntityPlayer player :
                supertile.getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(getPos().add(-3,-3,-3),getPos().add(3,3,3)))){

            if(mana<10)
                break;

            float health = -1;

            SHIT:
            for(PotionEffect e : player.getActivePotionEffects()){
                if(e.getPotion() == MobEffects.HEALTH_BOOST){

                    if(e.getDuration()>20)
                        return;
                    else {
                        health = player.getHealth();
                        player.removePotionEffect(MobEffects.HEALTH_BOOST);
                    }

                    break SHIT;
                }

            }

            player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 20*10, 2,false,false));
            if(health!=-1)
                player.setHealth(health);
            mana -= 500;

        }
    }

    @Override
    public int getMaxMana() {
        return 500;
    }


    @Override
    public LexiconEntry getEntry() {
        return EsuLexicon.LOTUSPEONY;
    }
}
