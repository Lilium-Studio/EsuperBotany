package net.lawaxi.esuperbotany.client.renderer.entity.boss.vazkii;

import net.lawaxi.esuperbotany.entity.boss.vazkii.EntityVazkii;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderVazkii extends RenderBiped<EntityVazkii>{

    private static final ResourceLocation SKIN = new ResourceLocation("esuperbotany:textures/entity/vazkii.png");

    public RenderVazkii(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelPlayer(0.0F, false), 0.0F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityVazkii entity) {
        return SKIN;
    }
}
