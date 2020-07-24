package net.lawaxi.esuperbotany.entity.boss;

import com.google.common.collect.ImmutableList;
import net.lawaxi.esuperbotany.entity.boss.vazkii.EntityVazkii;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.state.BotaniaStateProps;
import vazkii.botania.api.state.enums.PylonVariant;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.network.PacketBotaniaEffect;
import vazkii.botania.common.network.PacketHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BossCheckingHelper {

    private static final double r = EntityDoppleganger.ARENA_RANGE;
    public static final List<BlockPos> PYLON_LOCATIONS = ImmutableList.of(
            new BlockPos(4, 1, 4),
            new BlockPos(4, 1, -4),
            new BlockPos(-4, 1, 4),
            new BlockPos(-4, 1, -4)
    );

    //改自 EntityDoppleganger.class
    private static int countGaiaGuardiansAround(World world, BlockPos source) {
        float range = 15.0F;
        return
                world.getEntitiesWithinAABB(EntityDoppleganger.class, new AxisAlignedBB((double)source.getX() + 0.5D - (double)range, (double)source.getY() + 0.5D - (double)range, (double)source.getZ() + 0.5D - (double)range, (double)source.getX() + 0.5D + (double)range, (double)source.getY() + 0.5D + (double)range, (double)source.getZ() + 0.5D + (double)range)).size()+
                        world.getEntitiesWithinAABB(EntityVazkii.class, new AxisAlignedBB((double)source.getX() + 0.5D - (double)range, (double)source.getY() + 0.5D - (double)range, (double)source.getZ() + 0.5D - (double)range, (double)source.getX() + 0.5D + (double)range, (double)source.getY() + 0.5D + (double)range, (double)source.getZ() + 0.5D + (double)range)).size();
    }

    public static boolean checkSpawn(EntityPlayer player, ItemStack stack, World world, BlockPos pos) {
        if (world.getTileEntity(pos) instanceof TileEntityBeacon && isTruePlayer(player) && countGaiaGuardiansAround(world, pos) <= 0) {
            if (world.getDifficulty() == EnumDifficulty.PEACEFUL) {
                if (!world.isRemote) {
                    player.sendMessage((new TextComponentTranslation("botaniamisc.peacefulNoob", new Object[0])).setStyle((new Style()).setColor(TextFormatting.RED)));
                }

                return false;
            } else {
                List<BlockPos> invalidPylonBlocks = checkPylons(world, pos);
                if (!invalidPylonBlocks.isEmpty()) {
                    if (world.isRemote) {
                        warnInvalidBlocks(invalidPylonBlocks);
                    } else {
                        player.sendMessage((new TextComponentTranslation("botaniamisc.needsCatalysts", new Object[0])).setStyle((new Style()).setColor(TextFormatting.RED)));
                    }

                    return false;
                } else {
                    List<BlockPos> invalidArenaBlocks = checkArena(world, pos);
                    if (!invalidArenaBlocks.isEmpty()) {
                        if (world.isRemote) {
                            warnInvalidBlocks(invalidArenaBlocks);
                        } else {
                            PacketHandler.sendTo((EntityPlayerMP)player, new PacketBotaniaEffect(PacketBotaniaEffect.EffectType.ARENA_INDICATOR, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), new int[0]));
                            player.sendMessage((new TextComponentTranslation("botaniamisc.badArena", new Object[0])).setStyle((new Style()).setColor(TextFormatting.RED)));
                        }

                        return false;
                    } else {
                        return true;
                    }
                }
            }
        } else {
            return false;
        }
    }



    private static List<BlockPos> checkArena(World world, BlockPos beaconPos) {
        List<BlockPos> trippedPositions = new ArrayList();
        int range = (int)Math.ceil(r);

        for(int x = -range; x <= range; ++x) {
            for(int z = -range; z <= range; ++z) {
                if ((Math.abs(x) != 4 || Math.abs(z) != 4) && MathHelper.pointDistancePlane((double)x, (double)z, 0.0D, 0.0D) <= 12.0F) {
                    boolean hasFloor = false;

                    for(int y = -2; y <= 5; ++y) {
                        if (x != 0 || y != 0 || z != 0) {
                            BlockPos pos = beaconPos.add(x, y, z);
                            IBlockState state = world.getBlockState(pos);
                            boolean allowBlockHere = y < 0;
                            boolean isBlockHere = state.getCollisionBoundingBox(world, pos) != null;
                            if (allowBlockHere && isBlockHere) {


                                hasFloor = true;
                            }

                            if (y == 0 && !hasFloor) {
                                trippedPositions.add(pos.down());
                            }

                            if (!allowBlockHere && isBlockHere && !BotaniaAPI.gaiaBreakBlacklist.contains(state.getBlock())) {
                                trippedPositions.add(pos);
                            }
                        }
                    }
                }
            }
        }

        return trippedPositions;
    }


    private static void warnInvalidBlocks(Iterable<BlockPos> invalidPositions) {
        Botania.proxy.setWispFXDepthTest(false);
        Iterator var1 = invalidPositions.iterator();

        while(var1.hasNext()) {
            BlockPos pos_ = (BlockPos)var1.next();
            Botania.proxy.wispFX((double)pos_.getX() + 0.5D, (double)pos_.getY() + 0.5D, (double)pos_.getZ() + 0.5D, 1.0F, 0.2F, 0.2F, 0.5F, 0.0F, 8.0F);
        }

        Botania.proxy.setWispFXDepthTest(true);
    }


    private static List<BlockPos> checkPylons(World world, BlockPos beaconPos) {
        List<BlockPos> invalidPylonBlocks = new ArrayList();

        for (BlockPos coords : PYLON_LOCATIONS) {
            BlockPos pos = beaconPos.add(coords);

            IBlockState state = world.getBlockState(pos);
            if (state.getBlock() != ModBlocks.pylon || !(state.getProperties().get(BotaniaStateProps.PYLON_VARIANT) == PylonVariant.GAIA)) {
                invalidPylonBlocks.add(pos);
            }
        }

        return invalidPylonBlocks;
    }


    public static boolean isTruePlayer(Entity e) {
        return e instanceof EntityPlayer && !(e instanceof FakePlayer);
    }
}
