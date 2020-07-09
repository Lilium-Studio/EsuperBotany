package net.lawaxi.esuperbotany.entity;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import vazkii.botania.common.entity.EntityEnderAirBottle;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EntityHellAirBottle extends EntityEnderAirBottle {

    public final ItemStack bottle;

    public EntityHellAirBottle(World world) {
        super(world);
        this.bottle = new ItemStack(EsuCommons.RESOURCE,1,1);
    }


    public EntityHellAirBottle(World world, EntityPlayer player,ItemStack stack) {
        super(world,player);
        this.bottle = stack;
    }

    public EntityHellAirBottle(World world, EntityPlayer player) {
        super(world,player);
        this.bottle = new ItemStack(EsuCommons.RESOURCE,1,1);
    }

    @Override
    protected void onImpact(@Nonnull RayTraceResult pos) {
        if (pos.typeOfHit == RayTraceResult.Type.BLOCK && !world.isRemote) {

            List<BlockPos> coordsList = getCoordsToPut(((RayTraceResult) pos).getBlockPos());
            world.playEvent(2002, new BlockPos(this), 8);

            for (BlockPos coords : coordsList) {

                //生成方块
                world.setBlockState(coords, newHellBlock());

                //粒子效果
                if (Math.random() < 0.1) {
                    world.playEvent(2001, coords, Block.getStateId(Blocks.NETHERRACK.getDefaultState()));
                }
            }
            this.setDead();
        }
    }

    private List<BlockPos> getCoordsToPut(BlockPos pos) {
        List<BlockPos> possibleCoords = new ArrayList<>();
        int range = 4;
        int rangeY = 4;

        for (BlockPos bPos : BlockPos.getAllInBoxMutable(pos.add(-range, -rangeY, -range),
                pos.add(range, rangeY, range))) {
            IBlockState state = world.getBlockState(bPos);
            Block block = state.getBlock();
            if (block.isReplaceableOreGen(state, world, bPos, BlockStateMatcher.forBlock(Blocks.STONE))) {
                possibleCoords.add(bPos.toImmutable());
            }
        }

        Collections.shuffle(possibleCoords, rand);

        return possibleCoords.stream().limit(64).collect(Collectors.toList());
    }

    private static final String[] blocks = {"netherrack","netherrack","netherrack","netherrack","netherrack","quartz_ore"};
    private IBlockState newHellBlock(){
        return Block.getBlockFromName(blocks[rand.nextInt(blocks.length)]).getDefaultState();
    }
}
