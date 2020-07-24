package net.lawaxi.esuperbotany.client.renderer.entity.boss.vazkii;

import net.lawaxi.esuperbotany.entity.boss.vazkii.EntityFlowerMissile;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.ItemStack;

public class RenderFlowerMissile extends RenderSnowball<EntityFlowerMissile> {


    public RenderFlowerMissile(RenderManager renderManagerIn, RenderItem itemRendererIn) {
        super(renderManagerIn, null, itemRendererIn);
    }


    @Override
    public ItemStack getStackToRender(EntityFlowerMissile entityIn) {
        return entityIn.flower;
    }
}
