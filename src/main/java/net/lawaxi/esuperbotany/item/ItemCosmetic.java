package net.lawaxi.esuperbotany.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import vazkii.botania.api.item.ICosmeticBauble;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

public class ItemCosmetic extends CommonItem implements ICosmeticBauble, IBauble {

    public static final String[] names = {"redScarf","flowerCollector"};
    public static final BaubleType[] types= {BaubleType.TRINKET,BaubleType.TRINKET};

    //BaubleType.TRINKET 为任意

    public ItemCosmetic() {

        super("esuperbotany:cosmetic");
        setHasSubtypes(true);
        setMaxStackSize(1);
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


        switch (itemStack.getMetadata()) {
            case 0: {

                Helper.translateToChest();
                Helper.defaultTransforms();


                //x:向左为正 y:向上为正 z:向身前为正
                GlStateManager.translate(0.0, -0.15, +0.15); //位置

                //x:左右 y:上下 z:前后 都应为正 否则物品将会颠倒
                GlStateManager.scale(1.0F, 1.0F, 0.0F);       //缩放倍数

                renderItem(entityPlayer,itemStack);
                break;
            }
            /*
            case 1:{

                //Helper.translateToHeadLevel(entityPlayer);
                Helper.translateToFace();
                Helper.defaultTransforms();

                GlStateManager.translate(0.0, +0.5, +0.15);
                GlStateManager.scale(2.0F, 2.0F, 0.0F);
                break;
            }*/
        }

    }

    public void renderItem(EntityPlayer player,ItemStack stack) {

        Minecraft.getMinecraft().getItemRenderer().renderItem(player,stack, ItemCameraTransforms.TransformType.NONE);
    }


    private static final int FLOWERCOLLECTOR_cost = 5;
    private static final int FLOWERCOLLECTOR_range = 5;

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {


        switch (stack.getMetadata()){
            case 1:{

                if(entityIn instanceof EntityPlayer) {

                    ItemStack stack1 = ((EntityPlayer) entityIn).getHeldItemMainhand();

                    //需要手持采花袋
                    if (stack1.getItem() == ModItems.flowerBag) {

                        BlockPos a = entityIn.getPosition();
                        for (int i = -FLOWERCOLLECTOR_range; i <= FLOWERCOLLECTOR_range; i++) {
                            for (int j = -FLOWERCOLLECTOR_range; j <= FLOWERCOLLECTOR_range; j++) {

                                BlockPos pos = a.add(i, 0, j);

                                if (worldIn.isAirBlock(pos))
                                    continue;

                                IBlockState b = worldIn.getBlockState(pos);
                                if (b.getBlock() != ModBlocks.flower) {
                                    continue;
                                }


                                int color = b.getBlock().getMetaFromState(b);
                                if (color > 15) {
                                    continue;
                                }

                                if (!ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer) entityIn, FLOWERCOLLECTOR_cost, false)) {
                                    continue; //魔力不够了
                                }

                                IItemHandler bagInv = (IItemHandler) stack1.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, (EnumFacing) null);
                                ItemStack result = bagInv.insertItem(color, new ItemStack(Item.getItemFromBlock(ModBlocks.flower), 1, color), false);
                                if (result.getCount() == 1) {
                                    continue; //包装不下了
                                }

                                worldIn.playEvent(2001, pos, Block.getStateId(b));
                                worldIn.setBlockToAir(pos);
                            }
                        }
                    }
                }
            }
        }

        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }
}
