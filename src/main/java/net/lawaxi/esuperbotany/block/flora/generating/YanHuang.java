package net.lawaxi.esuperbotany.block.flora.generating;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.lawaxi.esuperbotany.utils.register.EsuLexicon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileGenerating;

public class YanHuang extends SubTileGenerating {


    /*

        产能花：炎黄

        炼铜产魔,掉落物:铜锭+10,铜矿+12,铜块+90;方块:铜矿+10,铜块+90
        消歧义：并非指方块学园炎黄蜀黍 也不是说吴梦南炼铜

     */

    private int delay = 0;

    private static final int[] range = {-1,0,1};

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(delay==0){
            if(mana<getMaxMana()){

                //掉落物
                for(EntityItem item :
                        supertile.getWorld().getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(getPos().add(-3,-3,-3),getPos().add(4,4,4)))){

                    if(item.getItem().getItem() == EsuCommons.COPPERINGOT){
                        item.getItem().shrink(1);
                        delay+=20*1;
                        mana+=10;
                        return; //为了延时稳定 需要在检测到一个后立刻停止
                    }
                    if(item.getItem().getItem() == Item.getItemFromBlock(EsuCommons.COPPERORE)){
                        item.getItem().shrink(1);
                        delay+=20*1;
                        mana+=12;
                        return;
                    }
                    if(item.getItem().getItem() == Item.getItemFromBlock(EsuCommons.COPPERBLOCK)){
                        item.getItem().shrink(1);
                        delay+=20*3;
                        mana+=90;
                        return;
                    }
                }

                //方块
                for(int i:range){
                    for(int j:range){
                        for(int k:range){

                            if(i==j && j==k && k==0){
                                continue;
                            }

                            BlockPos pos = getPos().add(i,j,k);
                            if(getWorld().getBlockState(pos).getBlock() == EsuCommons.COPPERBLOCK){

                                getWorld().setBlockState(pos, Blocks.AIR.getDefaultState());
                                delay+=20*1;
                                mana+=90;
                                return;
                            }

                            if(getWorld().getBlockState(pos).getBlock() == EsuCommons.COPPERORE){
                                getWorld().setBlockState(pos, Blocks.AIR.getDefaultState());
                                delay+=20*3;
                                mana+=10;
                                return;
                            }
                        }

                    }
                }

            }
        }else {
            delay--;
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
        return EsuLexicon.YANHUANG;
    }
}
