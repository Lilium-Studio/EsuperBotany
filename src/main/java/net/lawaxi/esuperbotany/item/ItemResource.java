package net.lawaxi.esuperbotany.item;

import net.lawaxi.esuperbotany.entity.EntityHellAirBottle;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.common.entity.EntityEnderAirBottle;

public class ItemResource extends CommonItem implements IFlowerComponent {

    public static final String[] names = {"manaEmerald","netherAirBottle","lytPicture","pension"};


    public ItemResource() {

        super("esuperbotany:resource");
        setHasSubtypes(true);

        for(int i=0;i<names.length;i++){
            ModelLoader.setCustomModelResourceLocation(this,i,new ModelResourceLocation("esuperbotany:"+names[i], "inventory"));
        }

        MinecraftForge.EVENT_BUS.register(this);
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
        if(stack.getMetadata() == 1){
            worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
            if(!worldIn.isRemote){
                EntityEnderAirBottle b = new EntityHellAirBottle(worldIn, playerIn);
                b.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                worldIn.onEntityAdded(b);
            }else{
                playerIn.swingArm(handIn);
            }
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
        }

        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }
}