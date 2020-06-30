package net.lawaxi.esuperbotany.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.model.ModelLoader;
import vazkii.botania.api.item.ICosmeticBauble;

public class ItemCosmetic extends CommonItem implements ICosmeticBauble, IBauble {

    public static final String[] names = {"redScarf"};
    public static final BaubleType[] types= {BaubleType.TRINKET};

    //BaubleType.TRINKET 为任意

    public ItemCosmetic() {

        super("esuperbotany:cosmetic");
        setHasSubtypes(true);
        setMaxStackSize(1);

        for(int i=0;i<names.length;i++){
            ModelLoader.setCustomModelResourceLocation(this,i,new ModelResourceLocation("esuperbotany:"+names[i], "inventory"));
        }
    }

    /*
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        for(int i=0;i<names.length;i++){
            items.add(new ItemStack(this,1,i));
        }
    }*/

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return types[itemStack.getMetadata()%types.length];
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "item.esuperbotany:"+names[stack.getMetadata()%names.length];
    }

    @Override
    public void onEquipped(ItemStack stack, EntityLivingBase player) {


        switch (stack.getMetadata()){
            case 0:
            {
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, Integer.MAX_VALUE, 2,false,false));
                break;
            }
        }
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {

        switch (stack.getMetadata()){
            case 0:{
                player.removePotionEffect(MobEffects.RESISTANCE);
                break;
            }
        }
    }

    @Override
    public void onPlayerBaubleRender(ItemStack itemStack, EntityPlayer entityPlayer, RenderType renderType, float v) {

        if(getBaubleType(itemStack)==BaubleType.HEAD){
            Helper.translateToHeadLevel(entityPlayer);
            Helper.translateToFace();
            Helper.defaultTransforms();

        }else{

            Helper.rotateIfSneaking(entityPlayer);
            Helper.translateToChest();
            Helper.defaultTransforms();
        }


        switch (itemStack.getMetadata()) {
            case 0: {

                //x:向左为正 y:向上为正 z:向身前为正
                GlStateManager.translate(0.0, -0.15, +0.15); //位置

                //x:左右 y:上下 z:前后 都应为正 否则物品将会颠倒
                GlStateManager.scale(1.0F, 1.0F, 0.0F);       //缩放倍数
                renderItem(entityPlayer,itemStack);
                break;
            }
        }
    }

    public void renderItem(EntityPlayer player,ItemStack stack) {
        Minecraft.getMinecraft().getItemRenderer().renderItem(player,stack, ItemCameraTransforms.TransformType.NONE);
    }


}
