package net.lawaxi.esuperbotany.entity.boss.util;

import io.netty.buffer.ByteBuf;
import net.lawaxi.esuperbotany.entity.boss.BossCheckingHelper;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.lawaxi.esuperbotany.utils.register.minecraft.EsuSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.ARBShaderObjects;
import vazkii.botania.api.boss.IBotaniaBoss;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityDoppleganger;

import java.awt.*;
import java.util.List;
import java.util.*;

public abstract class CommonBoss extends EntityLiving implements IBotaniaBoss, IEntityAdditionalSpawnData {


    @SideOnly(Side.CLIENT)
    public static final ResourceLocation bossBarTexture = new ResourceLocation("botania:textures/gui/bossBar.png");
    public SoundEvent BGM = EsuSounds.HISTORY_OF_MOON;


    protected BossInfoServer bossInfo;
    protected UUID bossInfoUUID;
    protected Rectangle barRect;
    protected Rectangle hpBarRect;
    @SideOnly(Side.CLIENT)
    protected ShaderCallback shaderCallback;


    protected int teleport_normal = 40;
    protected int teleport_damged = 4;
    protected int tpDelay = 40;

    protected int heal_max = 5;
    protected int healDelay = 40;

    //----------------------------------------------------------------------------------------------------------------------------------


    protected ArrayList<UUID> playersWhoAttacked = new ArrayList<>();
    protected BlockPos source = BlockPos.ORIGIN;
    protected int playerCount = 0;
    protected EntityPlayer trueKiller = null;

    public CommonBoss(World worldIn) {
        super(worldIn);
    }

    public CommonBoss(World world, BlockPos source, String name) {
        super(world);
        this.source = source;
        this.bossInfo = (BossInfoServer)(new BossInfoServer(new TextComponentTranslation("entity.esuperbotany:"+name + ".name", new Object[0]), BossInfo.Color.PINK, BossInfo.Overlay.PROGRESS)).setCreateFog(true);
        this.bossInfoUUID = bossInfo.getUniqueId();

        this.setSize(0.6F, 1.8F);
        this.isImmuneToFire = true;
        this.experienceValue = 300;
        if (world.isRemote) {
            Botania.proxy.addBoss(this);
        }


        this.playerCount = getPlayersAround().size();
    }

    public CommonBoss(World world,String name) {
        super(world);
        this.bossInfo = (BossInfoServer)(new BossInfoServer(new TextComponentTranslation("entity.esuperbotany:"+name+".name", new Object[0]), BossInfo.Color.PINK, BossInfo.Overlay.PROGRESS)).setCreateFog(true);
        this.bossInfoUUID = this.bossInfo.getUniqueId();

        this.setSize(0.6F, 1.8F);
        this.isImmuneToFire = true;
        this.experienceValue = 825;
        if (world.isRemote) {
            Botania.proxy.addBoss(this);
        }


        this.playerCount = getPlayersAround().size();
    }

    public List<EntityPlayer> getPlayersAround() {
        float range = 15.0F;
        return this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB((double)this.source.getX() + 0.5D - (double)range, (double)this.source.getY() + 0.5D - (double)range, (double)this.source.getZ() + 0.5D - (double)range, (double)this.source.getX() + 0.5D + (double)range, (double)this.source.getY() + 0.5D + (double)range, (double)this.source.getZ() + 0.5D + (double)range), (player) -> {
            return BossCheckingHelper.isTruePlayer(player) && !player.isSpectator();
        });
    }

    //----------------------------------------------------------------------------------------------------------------------------------

    @Override
    public ShaderCallback getBossBarShaderCallback(boolean b, int i) {
        if (shaderCallback == null)
            shaderCallback = shader1 -> {
                int hpFractUniform = ARBShaderObjects.glGetUniformLocationARB(shader1, "hpFract");
                ARBShaderObjects.glUniform1fARB(hpFractUniform, getHealth() / getMaxHealth());
            };

        return b ? null : shaderCallback;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Rectangle getBossBarTextureRect() {
        if(barRect == null)
            barRect = new Rectangle(0, 0, 185, 15);
        return barRect;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Rectangle getBossBarHPTextureRect() {
        if(hpBarRect == null)
            hpBarRect = new Rectangle(0, barRect.y + barRect.height, 181, 7);
        return hpBarRect;
    }

    @Override
    public ResourceLocation getBossBarTexture() {
        return bossBarTexture;
    }

    @Override
    public int bossBarRenderCallback(ScaledResolution scaledResolution, int x, int y) {

        GlStateManager.pushMatrix();
        int px = x + 160;
        int py = y + 12;

        Minecraft mc = Minecraft.getMinecraft();
        ItemStack stack = new ItemStack(Items.SKULL, 1, 3);
        mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.enableRescaleNormal();
        mc.getRenderItem().renderItemIntoGUI(stack, px, py);
        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();

        boolean unicode = mc.fontRenderer.getUnicodeFlag();
        mc.fontRenderer.setUnicodeFlag(true);
        mc.fontRenderer.drawStringWithShadow("" + playerCount, px + 15, py + 4, 0xFFFFFF);
        mc.fontRenderer.setUnicodeFlag(unicode);
        GlStateManager.popMatrix();

        return 5;
    }


    @Override
    @SideOnly(Side.CLIENT)
    public int getBossBarShaderProgram(boolean background) {
        return background ? 0 : ShaderHelper.dopplegangerBar;
    }


    @Override
    public UUID getBossInfoUuid() {
        return bossInfoUUID;
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(playerCount);
        buffer.writeLong(source.toLong());
        buffer.writeLong(bossInfoUUID.getMostSignificantBits());
        buffer.writeLong(bossInfoUUID.getLeastSignificantBits());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void readSpawnData(ByteBuf additionalData) {
        playerCount = additionalData.readInt();
        source = BlockPos.fromLong(additionalData.readLong());
        long msb = additionalData.readLong();
        long lsb = additionalData.readLong();
        bossInfoUUID = new UUID(msb, lsb);
        Minecraft.getMinecraft().getSoundHandler().playSound(new BossBGM(this,BGM));
    }

    @SideOnly(Side.CLIENT)
    private static class BossBGM extends MovingSound {
        CommonBoss boss;

        public BossBGM(CommonBoss boss, SoundEvent event) {
            super(event,SoundCategory.RECORDS);
            this.boss = boss;
            this.xPosF = boss.source.getX();
            this.yPosF = boss.source.getY();
            this.zPosF = boss.source.getZ();
            this.repeat = true;
        }

        @Override
        public void update() {
            if (!boss.isEntityAlive()) {
                donePlaying = true;
            }
        }
    }


    //----------------------------------------------------------------------------------------------------------------------------------


    protected void teleportRandomly() {
        double oldX = this.posX;
        double oldY = this.posY;
        double oldZ = this.posZ;
        double newY = (double)this.source.getY();
        int tries = 0;

        double newX;
        double newZ;
        do {
            newX = (double)this.source.getX() + (this.rand.nextDouble() - 0.5D) * 12.0D;
            newZ = (double)this.source.getZ() + (this.rand.nextDouble() - 0.5D) * 12.0D;
            tries++;
        } while(tries < 50 && MathHelper.pointDistanceSpace(newX, newY, newZ, (double)this.source.getX(), (double)this.source.getY(), (double)this.source.getZ()) > 12.0F);

        if (tries == 50) {
            newX = (double)this.source.getX() + 0.5D;
            newY = (double)this.source.getY() + 1.6D;
            newZ = (double)this.source.getZ() + 0.5D;
        }

        BlockPos tentativeFloorPos = new BlockPos(newX, newY - 1.0D, newZ);
        if (this.world.getBlockState(tentativeFloorPos).getCollisionBoundingBox(this.world, tentativeFloorPos) == null) {
            --newY;
        }

        this.setPosition(newX, newY, newZ);
        this.world.playSound((EntityPlayer)null, oldX, oldY, oldZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
        this.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
        Random random = this.getRNG();
        int particleCount = 128;


        for(int i = 0; i < particleCount; ++i) {
            double progress = (double)i / (double)(particleCount - 1);
            float vx = (random.nextFloat() - 0.5F) * 0.2F;
            progress = (random.nextFloat() - 0.5F) * 0.2F;
            float vz = (random.nextFloat() - 0.5F) * 0.2F;
            double px = oldX + (newX - oldX) * progress + (random.nextDouble() - 0.5D) * (double)this.width * 2.0D;
            double py = oldY + (newY - oldY) * progress + random.nextDouble() * (double)this.height;
            double pz = oldZ + (newZ - oldZ) * progress + (random.nextDouble() - 0.5D) * (double)this.width * 2.0D;
            this.world.spawnParticle(EnumParticleTypes.PORTAL, px, py, pz, (double)vx, (double)progress, (double)vz, new int[0]);
        }

        Vec3d oldPosVec = new Vec3d(oldX, oldY + (double)(this.height / 2.0F), oldZ);
        Vec3d newPosVec = new Vec3d(newX, newY + (double)(this.height / 2.0F), newZ);
        if (oldPosVec.squareDistanceTo(newPosVec) > 1.0D) {
            Iterator var19 = this.getPlayersAround().iterator();

            while(var19.hasNext()) {
                EntityPlayer player = (EntityPlayer)var19.next();
                RayTraceResult rtr = player.getEntityBoundingBox().grow(0.25D).calculateIntercept(oldPosVec, newPosVec);
                if (rtr != null) {
                    player.attackEntityFrom(DamageSource.causeMobDamage(this), 6.0F);
                }
            }

            /*
            int breakSteps = (int)oldPosVec.distanceTo(newPosVec);
            if (breakSteps >= 2) {
                for(int i = 0; i < breakSteps; ++i) {
                    float progress = (float)i / (float)(breakSteps - 1);
                    int breakX = net.minecraft.util.math.MathHelper.floor(oldX + (newX - oldX) * (double)progress);
                    int breakY = net.minecraft.util.math.MathHelper.floor(oldY + (newY - oldY) * (double)progress);
                    int breakZ = net.minecraft.util.math.MathHelper.floor(oldZ + (newZ - oldZ) * (double)progress);
                    this.smashBlocksAround(breakX, breakY, breakZ, 1);
                }
            }*/
        }

    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if(world.isRemote) {
            particles();
            EntityPlayer player = Botania.proxy.getClientPlayer();
            if(getPlayersAround().contains(player))
                player.capabilities.isFlying &= player.capabilities.isCreativeMode;

        }

        bossInfo.setPercent(getHealth() / getMaxHealth());

        if(isRiding())
            dismountRidingEntity();

        if(world.getDifficulty() == EnumDifficulty.PEACEFUL)
            setDead();

        List<EntityPlayer> players = getPlayersAround();

        if(players.isEmpty() && !world.playerEntities.isEmpty())
            setDead();
        else {
            for(EntityPlayer player : players) {

                if(player.isPlayerSleeping()) player.wakeUpPlayer(true, true, false);

                //clearPotions(player);
                keepInsideArena(player);
                player.capabilities.isFlying &= player.capabilities.isCreativeMode;
            }
        }

        if(isDead)
            return;

        if(tpDelay>0) {
            tpDelay--;
            if (tpDelay == 0 && getHealth() > 0) {
                teleportRandomly();
                tpDelay = teleport_normal;
            }
        }

        if(healDelay>0) {
            healDelay--;
            if (healDelay == 0 && getHealth() > 0) {
                heal(rand.nextInt(heal_max));
                tpDelay = 40;
            }
        }


        for (EntityPlayer player : getPlayersAround()) {
            this.faceEntity(player, 360F, 360F);
            break;
        }

        for (EntityPlayer player : getPlayersAround())
            if (!playersWhoAttacked.contains(player.getUniqueID()))
                playersWhoAttacked.add(player.getUniqueID());
    }

    private void keepInsideArena(EntityPlayer player) {
        if(vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace(player.posX, player.posY, player.posZ, source.getX() + 0.5, source.getY() + 0.5, source.getZ() + 0.5) >= EntityDoppleganger.ARENA_RANGE) {
            Vector3 sourceVector = new Vector3(source.getX() + 0.5, source.getY() + 0.5, source.getZ() + 0.5);
            Vector3 playerVector = Vector3.fromEntityCenter(player);
            Vector3 motion = sourceVector.subtract(playerVector).normalize();

            player.motionX = motion.x;
            player.motionY = 0.2;
            player.motionZ = motion.z;
            player.velocityChanged = true;

            player.addPotionEffect(new PotionEffect(MobEffects.WITHER,5*20,2));
        }
    }

    private void particles() {
        for(int i = 0; i < 360; i += 8) {
            float r = 0.6F;
            float g = 0F;
            float b = 0.2F;
            float m = 0.15F;
            float mv = 0.35F;

            float rad = i * (float) Math.PI / 180F;
            double x = source.getX() + 0.5 - Math.cos(rad) * EntityDoppleganger.ARENA_RANGE;
            double y = source.getY() + 0.5;
            double z = source.getZ() + 0.5 - Math.sin(rad) * EntityDoppleganger.ARENA_RANGE;

            Botania.proxy.wispFX(x, y, z, r, g, b, 0.5F, (float) (Math.random() - 0.5F) * m, (float) (Math.random() - 0.5F) * mv, (float) (Math.random() - 0.5F) * m);
        }

    }

    @Override
    protected void damageEntity(DamageSource damageSrc, float damageAmount) {
        super.damageEntity(damageSrc, damageAmount);

        Entity attacker = damageSrc.getImmediateSource();
        if(attacker != null) {
            Vector3 thisVector = Vector3.fromEntityCenter(this);
            Vector3 playerVector = Vector3.fromEntityCenter(attacker);
            Vector3 motionVector = thisVector.subtract(playerVector).normalize().multiply(0.75);

            if(getHealth() > 0) {
                motionX = -motionVector.x;
                motionY = 0.5;
                motionZ = -motionVector.z;
                tpDelay = teleport_damged;
            }
        }
    }



    @Override
    public void setHealth(float health) {
        super.setHealth(Math.max(health, getHealth() - 20F));
    }


    @Override
    public void onKillCommand() {
        setHealth(0.0F);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {

        Entity entity = source.getTrueSource();
        if(entity instanceof EntityPlayer){

            EntityPlayer player = (EntityPlayer)entity;
            if(!playersWhoAttacked.contains(player.getUniqueID()))
                playersWhoAttacked.add(player.getUniqueID());

            if(vazkii.botania.common.core.helper.MathHelper.pointDistancePlane(player.posX, player.posZ,
                    this.source.getX(), this.source.getZ()) > EntityDoppleganger.ARENA_RANGE)
                player.attemptTeleport(this.source.getX(),this.source.getY(), this.source.getZ());


            return super.attackEntityFrom(source, Math.min(25,amount));
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public void onDeath(DamageSource cause) {

        playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 20F,
                (1F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
        world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, posX, posY, posZ, 1D, 0D, 0D);

        super.onDeath(cause);
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {

        if(source.getDamageType().equals("player") && source.getTrueSource()instanceof EntityPlayer)
            trueKiller = (EntityPlayer) source.getTrueSource();

        if(!world.isRemote){

            for(UUID player:playersWhoAttacked){

                EntityItem drop = new EntityItem(world);
                drop.setItem(new ItemStack(EsuCommons.DICE));
                EsuCommons.DICE.bindToUUID(player,drop.getItem());
                drop.setDefaultPickupDelay();
                drop.setPosition(posX,posY,posZ);
                world.spawnEntity(drop);
            }

        }
    }
}