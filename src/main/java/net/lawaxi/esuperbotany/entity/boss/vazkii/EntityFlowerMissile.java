package net.lawaxi.esuperbotany.entity.boss.vazkii;

import net.lawaxi.esuperbotany.EsuperBotany;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import vazkii.botania.common.entity.EntityMagicMissile;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import java.util.List;

public class EntityFlowerMissile extends EntityMagicMissile {

    public final String type;
    public final ItemStack flower;

    private static final String[] flower_types = {"endoflame","hydroangeas","dandelifeon","hopperhock"};

    public EntityFlowerMissile(World worldIn) {
        super(worldIn);
        setSize(3F, 3F);

        this.type = flower_types[rand.nextInt(flower_types.length)];
        this.flower = ItemBlockSpecialFlower.ofType(this.type);
    }

    public EntityFlowerMissile(EntityVazkii vazkii) {
        super(vazkii,true);
        setSize(1.0F,1.0F);

        this.thrower = vazkii;
        this.type = flower_types[rand.nextInt(flower_types.length)];
        this.flower = ItemBlockSpecialFlower.ofType(this.type);

    }


    @Override
    protected void onImpact(RayTraceResult result) {

        if(result.typeOfHit == RayTraceResult.Type.BLOCK){

            Block block = world.getBlockState(result.getBlockPos()).getBlock();
            if (!(block instanceof BlockBush) && !(block instanceof BlockLeaves)) {
                setDead();
            }

        }else if(result.entityHit!=null && result.entityHit!=thrower && result.entityHit instanceof EntityLivingBase){


            if(thrower!=null) {
                EntityLivingBase entity = (EntityLivingBase) result.entityHit;

                switch (this.type) {

                    case "endoflame":{
                        entity.setFire(7);
                        break;
                    }

                    case "hydroangeas": {
                        entity.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 5, 5));
                        break;
                    }

                    case "dandelifeon": {
                        entity.attackEntityFrom(DamageSource.causeMobDamage(thrower),entity.getHealth()/2);
                        break;
                    }

                    case "hopperhock":{
                        if(entity instanceof EntityPlayer){
                            EntityItem item =((EntityPlayer) entity).dropItem(true);
                            if(item!=null)
                                item.setPickupDelay(5*20);
                        }

                        break;
                    }

                }
            }
            setDead();
        }
    }

    @Override
    public boolean findTarget() {

        setTarget(null);

        double range = 12.0D;
        List<EntityPlayer> entities = world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(posX - range, posY - range, posZ - range, posX + range, posY + range, posZ + range));

        for(EntityPlayer player:entities){
            if(!player.isDead){
                setTarget(player);
                EsuperBotany.logger.warn(player.getDisplayName());
                break;
            }

        }

        return getTargetEntity()!=null;
    }
}
