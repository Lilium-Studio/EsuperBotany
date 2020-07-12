package net.lawaxi.esuperbotany.block.subtiles.fuctional;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import vazkii.botania.api.subtile.SubTileFunctional;

import java.util.Random;

public class Irrigator extends SubTileFunctional {


    private static final int[] range = {-1,0,+1};
    private static final Random random = new Random();

    private static final int min = 2;
    private static final int max = 20;
    private static final int per = 300;

    private int delay = 0;

    @Override
    public void onUpdate() {

        if(redstoneSignal>0){
            return;
        }

        super.onUpdate();

        if(delay==0) {

            boolean has = false;

            for (int i : range) {
                for (int j : range) {

                    if (i == j && i == 0)
                        continue;

                    if(mana<max*per)
                        break;

                    BlockPos a = getPos().add(i, -1, j);
                    if (getWorld().getBlockState(a).getBlock() == Blocks.WATER) {

                        int value = min + random.nextInt(max-min);

                        if (!getWorld().isRemote) {

                            EntityXPOrb xp = new EntityXPOrb(getWorld(), a.getX(), a.getY(), a.getZ(), value);
                            getWorld().spawnEntity(xp);
                        }
                        mana-=value*per;
                        has = true;
                    }
                }
            }

            if(has){
                delay+=40;
            }
        }else
            delay--;

    }

    @Override
    public int getMaxMana() {
        return 8*max*per;
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }
}
