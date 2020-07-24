package net.lawaxi.esuperbotany.block.subtiles.generating;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.ISubTileContainer;
import vazkii.botania.common.block.subtile.generating.SubTileRafflowsia;
import vazkii.botania.common.lexicon.LexiconData;

public class RaffRafflowsia extends SubTileRafflowsia {


    @Override
    public void onUpdate() {

        super.onUpdate();

        for(int i = 0; i < 11; ++i) {
            for (int j = 0; j < 11; ++j) {
                for (int k = 0; k < 11; ++k) {
                    BlockPos pos = this.supertile.getPos().add(i - 5, j - 5, k - 5);
                    TileEntity tile = this.supertile.getWorld().getTileEntity(pos);
                    if (tile instanceof ISubTileContainer) {

                        if(((ISubTileContainer) tile).getSubTile() instanceof RaffRafflowsia)
                        continue;


                        this.getWorld().destroyBlock(pos, false);
                        mana+=2100*2;
                        this.sync();
                        return;
                    }
                }
            }
        }
    }


    public int getColor() {
        return 5254262;
    }

    public int getMaxMana() {
        return 9000;
    }

    public LexiconEntry getEntry() {
        return LexiconData.rafflowsia;
    }
}
