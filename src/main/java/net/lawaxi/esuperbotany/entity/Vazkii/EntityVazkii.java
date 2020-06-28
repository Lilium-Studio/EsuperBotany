package net.lawaxi.esuperbotany.entity.Vazkii;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;
import vazkii.botania.api.boss.IBotaniaBoss;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.common.Botania;
import vazkii.botania.common.lib.LibEntityNames;

import java.awt.*;
import java.util.UUID;

public class EntityVazkii extends EntityLiving implements IBotaniaBoss{

    private BossInfoServer bossinfo;

    public EntityVazkii(World worldIn) {

        super(worldIn);
        Botania.proxy.addBoss(this);

        bossinfo = (BossInfoServer)(new BossInfoServer(new TextComponentTranslation("entity." + LibEntityNames.DOPPLEGANGER_REGISTRY + ".name", new Object[0]), BossInfo.Color.PINK, BossInfo.Overlay.PROGRESS)).setCreateFog(true);
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

}
