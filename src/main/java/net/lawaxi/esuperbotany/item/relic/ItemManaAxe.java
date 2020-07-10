package net.lawaxi.esuperbotany.item.relic;

import net.lawaxi.esuperbotany.api.EntityHelper;
import net.lawaxi.esuperbotany.item.ItemLootBag;
import net.lawaxi.esuperbotany.item.relic.util.CommonItemAxeRelic;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

public class ItemManaAxe extends CommonItemAxeRelic implements IManaUsingItem {

    public ItemManaAxe() {

        super(ToolMaterial.DIAMOND,"esuperbotany:manaaxe",false, DamgeType.HURT);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }

    private static final int repairCost = 85;
    private static final int cost = 150;

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entityIn, int itemSlot, boolean isSelected) {


        if(entityIn instanceof EntityPlayer) {
            if (!world.isRemote && itemStack.getItemDamage() >= 1 && ManaItemHandler.requestManaExactForTool(itemStack, (EntityPlayer) entityIn, repairCost, true)) {
                itemStack.setItemDamage(itemStack.getItemDamage() - 1);
            }
        }

        super.onUpdate(itemStack, world, entityIn, itemSlot, isSelected);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {

        if(attacker instanceof EntityPlayer){

            //有但不消耗
            if(ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) attacker, cost, false)){


                boolean ret = super.hitEntity(stack, target, attacker);

                if(ret && itemRand.nextInt(10)==0)
                {

                    World world = target.getEntityWorld();
                    EntityLightningBolt lightningBolt = new EntityLightningBolt(world,target.posX,target.posY,target.posZ,false);
                    world.spawnEntity(lightningBolt);
                }

                return ret;

            }
            return false;

        }else
            return super.hitEntity(stack, target, attacker);
    }

    @SubscribeEvent
    public void leftClick(PlayerInteractEvent.LeftClickEmpty e) {


        if (e.getItemStack().getItem() == this) {

            EntityLivingBase target = EntityHelper.findTarget(e.getEntityPlayer(), EntityLivingBase.class, 20);
            if (target != null && ManaItemHandler.requestManaExactForTool(e.getItemStack(), e.getEntityPlayer(), cost, true)) {

                //e.getEntityPlayer().attackTargetEntityWithCurrentItem(target);

                World world = target.getEntityWorld();
                EntityLightningBolt lightningBolt = new EntityLightningBolt(world,target.posX,target.posY,target.posZ,false);
                world.spawnEntity(lightningBolt);

            }
        }
    }


    @SubscribeEvent
    public void onEntityDrops(LivingDropsEvent event) {

        if (event.isRecentlyHit() && event.getSource().getTrueSource() != null && event.getSource().getTrueSource() instanceof EntityPlayer) {

            if(((EntityPlayer)event.getSource().getTrueSource()).getActiveItemStack().getItem()==this){

                if(itemRand.nextInt(3)==0){


                    EntityItem item = new EntityItem(
                            event.getEntityLiving().world,
                            event.getEntityLiving().posX,
                            event.getEntityLiving().posY,
                            event.getEntityLiving().posZ,
                            new ItemStack(EsuCommons.LOOTBAG,1,itemRand.nextInt(ItemLootBag.names.length))
                    );
                    item.setDefaultPickupDelay();
                    event.getDrops().add(item);
                }
            }
        }

    }
}
