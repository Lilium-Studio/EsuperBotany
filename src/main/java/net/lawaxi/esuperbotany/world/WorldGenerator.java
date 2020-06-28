package net.lawaxi.esuperbotany.world;

import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import java.util.Random;

public class WorldGenerator implements IWorldGenerator {

    //感谢 https://github.com/JinpaLhawang/coppermod

    private static int OVERWORLD_DIMENSION = 0;
    private static int COPPER_MIN_Y = 0;
    private static int COPPER_MAX_Y = 64;
    private static int COPPER_ORE_CHANCES = 60;


    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == OVERWORLD_DIMENSION)
        {
            int deltaY = COPPER_MAX_Y - COPPER_MIN_Y;
            int x = chunkX * 16;
            int z = chunkZ * 16;

            for (int i = 0; i < COPPER_ORE_CHANCES; i++) {
                BlockPos pos = new BlockPos(x + random.nextInt(16), COPPER_MIN_Y + random.nextInt(deltaY), z + random.nextInt(16));

                WorldGenMinable generator = new WorldGenMinable(EsuCommons.COPPERORE.getDefaultState(), 4 + random.nextInt(4));
                generator.generate(world, random, pos);
            }
        }
    }
}
