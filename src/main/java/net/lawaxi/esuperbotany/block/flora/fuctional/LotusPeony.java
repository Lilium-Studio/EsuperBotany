package net.lawaxi.esuperbotany.block.flora.fuctional;

import net.lawaxi.esuperbotany.utils.register.EsuLexicon;
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

            boolean has = false;
            for(PotionEffect e : player.getActivePotionEffects()){
                if(e.getPotion() == MobEffects.HEALTH_BOOST){
                    has = true;
                }

            }

            if(!has) {
                player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 20*10, 5,false,false));
                mana -= 20;
            }
        }
    }

    @Override
    public int getMaxMana() {
        return 20;
    }


    @Override
    public LexiconEntry getEntry() {
        return EsuLexicon.LOTUSPEONY;
    }
}
