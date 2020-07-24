package net.lawaxi.esuperbotany.item.equipment;

import net.lawaxi.esuperbotany.client.model.armor.ModelArmorXT;
import net.lawaxi.esuperbotany.entity.EntityXTHand;
import net.lawaxi.esuperbotany.network.EsuNetwork;
import net.lawaxi.esuperbotany.network.PacketXTHand;
import net.lawaxi.esuperbotany.utils.register.minecraft.EsuMaterial;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

import javax.annotation.Nullable;

public class ArmorXT extends CommonArmor implements IManaUsingItem {

    public ArmorXT(EntityEquipmentSlot type) {
        super("xt",null, EsuMaterial.ARMOR_XT, type,85);


        //只注册到其中一个上 否则会发射四次
        if(type==EntityEquipmentSlot.HEAD) {
            MinecraftForge.EVENT_BUS.register(this);
        }
    }

    @SubscribeEvent
    public void leftClick(PlayerInteractEvent.LeftClickEmpty e) {

        //空手 全套装备 魔力
        if(!e.getItemStack().isEmpty())
            return;

        EntityPlayer player = e.getEntityPlayer();

        if(wearAll(player) && ManaItemHandler.requestManaExactForTool(player.inventory.armorInventory.get(0), player, cost, true)){
            EsuNetwork.sendMessageToServer(new PacketXTHand());
        }
    }

    public static void shoot(EntityPlayerMP player){

        EntityXTHand hand = new EntityXTHand(player.getEntityWorld(),player);
        hand.shoot(player,player.rotationPitch,player.rotationYaw,0.0F, 0.5F, 1.0F);
        player.getEntityWorld().spawnEntity(hand);
    }

    @SideOnly(Side.CLIENT)
    @Nullable
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {

        if(models.containsKey(armorSlot))
            return  models.get(armorSlot);
        else
            return models.put(armorSlot,new ModelArmorXT(armorSlot));
    }


    private static final int cost = 100;

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }


    @SideOnly(Side.CLIENT)
    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "esuperbotany:textures/models/armor/xt.png";
    }


}