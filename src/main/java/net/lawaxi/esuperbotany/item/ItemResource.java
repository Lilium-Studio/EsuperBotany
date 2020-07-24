package net.lawaxi.esuperbotany.item;

import net.lawaxi.esuperbotany.entity.EntityHellAirBottle;
import net.lawaxi.esuperbotany.entity.boss.vazkii.EntityVazkii;
import net.lawaxi.esuperbotany.item.util.CommonItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.common.entity.EntityEnderAirBottle;

import javax.annotation.Nullable;
import java.util.List;

public class ItemResource extends CommonItem implements IFlowerComponent {

    public static final String[] names = {"manaEmerald","netherAirBottle","lytPicture","pension","upMan"};


    public ItemResource() {

        super("esuperbotany:resource");
        setHasSubtypes(true);

        MinecraftForge.EVENT_BUS.register(this);
        OreDictionary.registerOre("manaEmerald", new ItemStack(this,0,0));
        OreDictionary.registerOre("elvenDragonpicture", new ItemStack(this,0,2));

    }

    @Override
    public int getParticleColor(ItemStack itemStack) {
        return 0x00CC00;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item.esuperbotany:"+names[stack.getMetadata()%names.length];
    }

    /*
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        for(int i=0;i<names.length;i++){
            items.add(new ItemStack(this,1,i));
        }
    }*/

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

            ItemStack stack = playerIn.getHeldItem(handIn);
            if (stack.getMetadata() == 1) {

                worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

                if (!worldIn.isRemote) {
                    //服务端
                    EntityEnderAirBottle b = new EntityHellAirBottle(worldIn, playerIn,stack);
                    b.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                    worldIn.spawnEntity(b);

                } else {
                    //客户端
                    playerIn.swingArm(handIn);
                }

                stack.shrink(1);
            }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @SubscribeEvent
    public void rightClick(PlayerInteractEvent.RightClickItem e) {

        if(!e.getItemStack().isEmpty()) {
            //瓶装地狱空气
            if ( e.getItemStack().getItem() == Items.GLASS_BOTTLE) {
                if (e.getWorld().provider instanceof WorldProviderHell) {
                    e.getItemStack().shrink(1);
                    e.getEntityPlayer().addItemStackToInventory(new ItemStack(this, 1, 1));
                }
            }
        }
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {

        //龙图
        if(stack.getMetadata()==2) {
            if (target instanceof EntityHorse) {
                stack.shrink(1);
                target.onKillCommand();
            }
        }/*else if(stack.getMetadata()==4){
            if(target instanceof EntityPlayer){
                stack.shrink(1);
                target.getPassengers().add(playerIn);
            }
        }*/

        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        ItemStack me = player.getHeldItem(hand);
        if(me.getMetadata()==4){
            return EntityVazkii.spawn(player, me, worldIn, pos) ? EnumActionResult.SUCCESS : EnumActionResult.FAIL;
        }

        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        switch (stack.getMetadata()) {

            case 2: {
                tooltip.add(TextFormatting.ITALIC + I18n.format("item.esuperbotany:lytPicture.lore"));
                break;
            }

            case 4:{
                tooltip.add(TextFormatting.ITALIC + I18n.format("item.esuperbotany:upMan.lore"));
                break;

            }

        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

}
