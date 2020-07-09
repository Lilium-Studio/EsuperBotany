package net.lawaxi.esuperbotany.client.renderer.entity;

import net.lawaxi.esuperbotany.entity.EntityHellAirBottle;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.ItemStack;

public class RenderHellAirBottle extends RenderSnowball<EntityHellAirBottle> {


    public RenderHellAirBottle(RenderManager renderManagerIn, RenderItem itemRendererIn) {
        super(renderManagerIn, EsuCommons.RESOURCE, itemRendererIn);
    }

    @Override
    public ItemStack getStackToRender(EntityHellAirBottle entityIn) {
        return entityIn.bottle;
    }
}