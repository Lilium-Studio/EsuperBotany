package net.lawaxi.esuperbotany.block.subtiles.fuctional;

import net.lawaxi.esuperbotany.api.EntityHelper;
import net.lawaxi.esuperbotany.utils.register.EsuLexicon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class Degausser extends SubTileFunctional {

    private static final int cost = 10000;
    private static final String TAG ="soulbindUUID";

    @Override
    public void onUpdate() {

        for(EntityItem item :
                getWorld().getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(getPos().add(-1,0,-1),getPos().add(1,1,1)))){

            if(item.getItem().getItem() instanceof IRelic
                && ((IRelic)item.getItem().getItem()).hasUUID(item.getItem())){

                if(mana<cost*item.getItem().getCount()){
                    super.onUpdate();

                }else{
                    mana-= cost*item.getItem().getCount();
                    ItemNBTHelper.removeEntry(item.getItem(),TAG);
                    EntityHelper.particleAround(item,EnumParticleTypes.END_ROD,true);
                }

                return;
            }

        }


    }

    @Override
    public int getMaxMana() {
        return cost;
    }


    @Override
    public LexiconEntry getEntry() {
        return EsuLexicon.DEGAUSSER;
    }
}
