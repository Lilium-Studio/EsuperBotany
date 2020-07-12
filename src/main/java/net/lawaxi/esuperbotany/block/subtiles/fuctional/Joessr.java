package net.lawaxi.esuperbotany.block.subtiles.fuctional;

import net.lawaxi.esuperbotany.block.BlockManaStorage;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.lawaxi.esuperbotany.utils.register.EsuLexicon;
import net.minecraft.util.math.BlockPos;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;

public class Joessr extends SubTileFunctional {

    @Override
    public void onUpdate() {

        if(redstoneSignal>0){
            return;
        }

        super.onUpdate();

        if(delay==0) {

            if (mana >= BlockManaStorage.default_mana) {

                BlockPos a= null;

                //计算周围位置
                SHIT:
                for(int i:range){
                    for(int j:range){

                        if(i==j && i==0){
                            continue;
                        }

                        BlockPos now = getPos().add(i,0,j);
                        if(getWorld().isAirBlock(now)){
                            a = now;
                            break SHIT;
                        }
                    }
                }

                if (a != null) {
                    getWorld().setBlockState(a, EsuCommons.MANASTORAGE.getDefaultState());
                    delay += 10;
                    mana -= BlockManaStorage.default_mana;
                }
            }
        }
        else{
            delay--;
        }

    }

    private int delay = 0;

    private static final int[] range = {-1,0,1};

    @Override
    public LexiconEntry getEntry() {
        return EsuLexicon.JOESSR;
    }

    @Override
    public int getMaxMana() {
        return BlockManaStorage.default_mana+20;
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }
}
