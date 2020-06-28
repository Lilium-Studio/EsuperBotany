package net.lawaxi.esuperbotany.client.renderer.entity.Vazkii;

import net.lawaxi.esuperbotany.entity.Vazkii.EntityVazkii;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderVazkii extends RenderBiped<EntityVazkii> {

    private static final ResourceLocation SKIN = new ResourceLocation("esuperbotany:textures/entity/vazkii.png");

    public RenderVazkii(RenderManager renderManagerIn) {
        super(renderManagerIn,
                new ModelPlayer(0.0F, false),
                0.0F);
        //植物魔法的BOSS都没有影子诶
    }

    @Override
    public void doRender(EntityVazkii entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityVazkii entity) {
        return SKIN;
    }
}
