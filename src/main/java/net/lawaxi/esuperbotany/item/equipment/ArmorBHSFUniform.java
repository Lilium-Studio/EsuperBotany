package net.lawaxi.esuperbotany.item.equipment;

import net.lawaxi.esuperbotany.api.EntityHelper;
import net.lawaxi.esuperbotany.utils.register.EsuMaterial;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

import javax.annotation.Nullable;

public class ArmorBHSFUniform extends CommonArmor implements IManaUsingItem {

    public ArmorBHSFUniform() {
        super(null, "bhsfUniform", EsuMaterial.BHSFUniform, EntityEquipmentSlot.CHEST, 80);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private static final int costXp = 50;
    private static final int costHurt = 50;
    private static final int costHurtKill = 10000;
    private static final int costHeal = 50;

    @SubscribeEvent
    public void onGetXP(PlayerPickupXpEvent e) {

        ItemStack stack = findme(e.getEntityPlayer());
        if(stack == null)
            return;


        if (ManaItemHandler.requestManaExactForTool(stack, e.getEntityPlayer(), costXp, true)) {
            e.getOrb().xpValue = e.getOrb().getXpValue() * 2;
        }
    }

    @SubscribeEvent
    public void onDamge(LivingHurtEvent e) {



        if(e.getEntityLiving() instanceof EntityPlayer){

            EntityPlayer player = (EntityPlayer)e.getEntityLiving();
            ItemStack stack = findme(player);
            if(stack == null)
                return;


            if(e.getSource()== DamageSource.OUT_OF_WORLD && e.getAmount()>=player.getHealth()){
                if(ManaItemHandler.requestManaExactForTool(stack, player, costHurtKill, true))
                {
                    e.setAmount(player.getHealth()/2);
                    EntityHelper.particleAround(player, EnumParticleTypes.VILLAGER_HAPPY);
                }
                return;
            }

            if(ManaItemHandler.requestManaExactForTool(stack, player, costHurt, true)){
                e.setAmount(e.getAmount()/2);
            }
        }
    }

    @SubscribeEvent
    public void onHeal(LivingHealEvent e) {



        if(e.getEntityLiving() instanceof EntityPlayer){

            EntityPlayer player = (EntityPlayer)e.getEntityLiving();
            ItemStack stack = findme(player);
            if(stack == null)
                return;

            if(ManaItemHandler.requestManaExactForTool(stack, player, costHeal, true)){
                e.setAmount(e.getAmount()*2);
            }
        }
    }

    @Nullable
    private ItemStack findme(EntityPlayer player){
        for(ItemStack stack : player.inventory.armorInventory){
            if(stack.getItem() == this)
                return stack;
        }
        return null;
    }


    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }



    @SideOnly(Side.CLIENT)
    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "esuperbotany:textures/models/armor/bhsf.png";
    }
}
