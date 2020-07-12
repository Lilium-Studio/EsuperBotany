package net.lawaxi.esuperbotany.client.renderer.entity.Vazkii;

import net.lawaxi.esuperbotany.entity.Vazkii.EntityVazkii;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderVazkii extends RenderBiped<EntityVazkii>{

    private static final ResourceLocation SKIN = new ResourceLocation("esuperbotany:textures/entity/vazkii.png");

    public RenderVazkii(RenderManager renderManagerIn) {
        super(renderManagerIn,
                new ModelPlayer(0.0F, false),
                0.0F);
    }

    @Override
    public void doRender(EntityVazkii entity, double x, double y, double z, float entityYaw, float partialTicks) {

        if(entity.longNeck > 0 ){
            ((ModelPlayer)this.mainModel).bipedHead.offsetX+=5F;
            ((ModelPlayer)this.mainModel).bipedHead.offsetX+=5F;
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityVazkii entity) {
        return SKIN;
    }
}
