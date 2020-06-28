package net.lawaxi.esuperbotany.block.flora.generating;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.lawaxi.esuperbotany.utils.register.EsuLexicon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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


    @Override
    public void onUpdate() {
        super.onUpdate();

        if(delay==0){
            if(mana<getMaxMana()){

                //掉落物
                for(EntityItem item :
                        supertile.getWorld().getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(getPos().add(-3,-3,-3),getPos().add(4,4,4)))){

                    if(item.getItem().getItem() == EsuCommons.COPPERINGOT){
                        deal(item);
                        delay+=20*1;
                        mana+=10;
                    }
                    if(item.getItem().getItem() == Item.getItemFromBlock(EsuCommons.COPPERORE)){
                        deal(item);
                        delay+=20*1;
                        mana+=12;
                    }
                    if(item.getItem().getItem() == Item.getItemFromBlock(EsuCommons.COPPERBLOCK)){
                        deal(item);
                        delay+=20*3;
                        mana+=90;
                    }
                }

                //方块
                int[] range = {-1,0,1};
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
                                continue;
                            }

                            if(getWorld().getBlockState(pos).getBlock() == EsuCommons.COPPERORE){
                                getWorld().setBlockState(pos, Blocks.AIR.getDefaultState());
                                delay+=20*3;
                                mana+=10;
                                continue;
                            }
                        }

                    }
                }

            }
        }else {
            delay--;
        }

    }

    private static void deal(EntityItem item){
        ItemStack i = item.getItem();
        if(i.getCount()==1){
            item.setDead();
        }
        else{
            i.setCount(i.getCount()-1);
            item.setItem(i);
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
