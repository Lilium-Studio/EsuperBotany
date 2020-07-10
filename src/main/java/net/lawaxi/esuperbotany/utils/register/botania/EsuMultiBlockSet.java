package net.lawaxi.esuperbotany.utils.register.botania;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.common.block.ModBlocks;

public class EsuMultiBlockSet {

    public static MultiblockSet EMPEROR;
    public static MultiblockSet EMPEROR2;

    public static void init(){

        EMPEROR = emperor();
        EMPEROR2 = emperor2();

    }

    private static final MultiblockSet emperor(){
        Multiblock a = new Multiblock();

        a.addComponent(new BlockPos(-2,1,0), ModBlocks.pool.getDefaultState());
        a.addComponent(new BlockPos(-1,1,0), ModBlocks.specialFlower.getDefaultState());
        a.addComponent(new BlockPos(1,0,0),Blocks.REDSTONE_WIRE.getDefaultState());
        a.addComponent(new BlockPos(0,0,0),reFace(Blocks.PISTON.getDefaultState(),EnumFacing.UP));
        a.addComponent(new BlockPos(1,1,0),reFace(Blocks.OBSERVER.getDefaultState(),EnumFacing.NORTH));
        a.addComponent(new BlockPos(2,1,0),reFace(Blocks.STICKY_PISTON.getDefaultState(),EnumFacing.DOWN));
        a.addComponent(new BlockPos(2,-1,0),Blocks.REDSTONE_BLOCK.getDefaultState());

        return a.makeSet();
    }

    private static final MultiblockSet emperor2(){
        Multiblock a = new Multiblock();

        a.addComponent(new BlockPos(1,0,0), ModBlocks.pool.getDefaultState());
        a.addComponent(new BlockPos(0,0,0),ModBlocks.specialFlower.getDefaultState());
        a.addComponent(new BlockPos(-1,-1,+1),reFace(Blocks.REDSTONE_TORCH.getDefaultState(),EnumFacing.EAST));
        a.addComponent(new BlockPos(+2,0,-1),Blocks.REDSTONE_WIRE.getDefaultState());
        a.addComponent(new BlockPos(-2,0,+2),Blocks.STONE.getDefaultState());
        a.addComponent(new BlockPos(-2,1,+2),reFace(Blocks.PISTON.getDefaultState(),EnumFacing.EAST));
        a.addComponent(new BlockPos(+1,0,-2),reFace(Blocks.PISTON.getDefaultState(),EnumFacing.SOUTH));

        return a.makeSet();

    }

    private static final IBlockState reFace(IBlockState pre,EnumFacing facing){
        for(IProperty a:pre.getPropertyKeys()){
            if(a.getName().equalsIgnoreCase("facing")){
                return pre.withProperty(a,facing);
            }
        }
        return pre;
    }
}
