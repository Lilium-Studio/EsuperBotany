package net.lawaxi.esuperbotany.block.subtiles.fuctional;

import net.lawaxi.esuperbotany.utils.register.EsuLexicon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;

public class Cucurbit extends SubTileFunctional {

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(mana<100)
            return;

        for(EntityPlayer player :
                getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(getPos().add(-3,-3,-3),getPos().add(3,3,3)))){

            if(player.getFoodStats().needFood()){

                player.getFoodStats().addStats((ItemFood) Item.getByNameOrId("beef"), new ItemStack(Items.BEEF));
                //EntityHelper.particleAround(player, EnumParticleTypes.VILLAGER_HAPPY,false);
                mana-=100;

                return;
            }
        }
    }

    @Override
    public int getMaxMana() {
        return 100;
    }

    @Override
    public LexiconEntry getEntry() {
        return EsuLexicon.CUCURBIT;
    }
}
