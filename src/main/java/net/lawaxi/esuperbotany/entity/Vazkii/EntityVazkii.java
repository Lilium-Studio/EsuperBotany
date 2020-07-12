package net.lawaxi.esuperbotany.entity.Vazkii;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import vazkii.botania.api.boss.IBotaniaBoss;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.common.Botania;
import vazkii.botania.common.lib.LibEntityNames;

import java.awt.*;
import java.util.UUID;

public class EntityVazkii extends EntityLiving implements IBotaniaBoss, IEntityAdditionalSpawnData {

    public int longNeck;
    private BossInfoServer bossinfo;

    public EntityVazkii(World worldIn) {

        super(worldIn);
        Botania.proxy.addBoss(this);

        bossinfo = (BossInfoServer)(new BossInfoServer(new TextComponentTranslation("entity." + LibEntityNames.DOPPLEGANGER_REGISTRY + ".name", new Object[0]), BossInfo.Color.PINK, BossInfo.Overlay.PROGRESS)).setCreateFog(true);
        if(worldIn.isRemote)
            Botania.proxy.addBoss(this);
    }

    @Override
    public Rectangle getBossBarHPTextureRect() {
        return null;
    }

    @Override
    public ResourceLocation getBossBarTexture() {
        return null;
    }


    @Override
    public Rectangle getBossBarTextureRect() {
        return null;
    }

    @Override
    public int bossBarRenderCallback(ScaledResolution scaledResolution, int i, int i1) {
        return 0;
    }

    @Override
    public UUID getBossInfoUuid() {
        return bossinfo.getUniqueId();
    }

    @Override
    public int getBossBarShaderProgram(boolean b) {
        return 0;
    }

    @Override
    public ShaderCallback getBossBarShaderCallback(boolean b, int i) {
        return null;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {

    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {

    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(longNeck > 0)
        {
            longNeck--;
        }else {


            for (EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(posX - 5, posY - 0.5, posZ - 5, posX + 5, posY + 0.5, posZ + 5))) {

                if (player.isCreative())
                    continue;

                if (!world.isRemote) {
                    getLookHelper().setLookPositionWithEntity(player, 0.0F, -1.0F);
                    longNeck = 20;
                    break;
                }

            }
        }
    }
}
