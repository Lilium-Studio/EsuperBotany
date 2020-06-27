package net.lawaxi.esuperbotany.common.item;

import net.lawaxi.esuperbotany.utils.register.EsuCreativeTab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;

import javax.annotation.Nullable;
import java.util.List;

public class ItemLZFishingRod extends ItemFishingRod implements IManaUsingItem {

    public ItemLZFishingRod() {

        String name = "esuperbotany:fishing_rod_lz";

        setUnlocalizedName(name);
        ForgeRegistries.ITEMS.register(this.setRegistryName(name));
        ModelLoader.setCustomModelResourceLocation(this,0,new ModelResourceLocation(name, "inventory"));
    }

    @Override
    public boolean usesMana(ItemStack itemStack) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.ITALIC + I18n.format(getUnlocalizedName()+".lore"));
    }


    // 代码照搬自Minecraft代码
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (playerIn.fishEntity != null)
        {
            if(ManaItemHandler.requestManaExactForTool(playerIn.getHeldItem(handIn), playerIn, 500, false)){

                //爆炸
                playerIn.fishEntity.getEntityWorld().createExplosion(playerIn.fishEntity,
                        playerIn.fishEntity.getPosition().getX(),playerIn.fishEntity.getPosition().getY(),playerIn.fishEntity.getPosition().getZ(),3F,true);

                itemstack.damageItem(playerIn.fishEntity.handleHookRetraction(), playerIn);
                playerIn.swingArm(handIn);

            }else{

                //普通钓鱼
                int i = playerIn.fishEntity.handleHookRetraction();
                itemstack.damageItem(i, playerIn);
                playerIn.swingArm(handIn);
                worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));


            }
            /**/

        }
        else
        {
            worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!worldIn.isRemote)
            {
                EntityFishHook entityfishhook = new EntityFishHook(worldIn, playerIn);
                int j = EnchantmentHelper.getFishingSpeedBonus(itemstack);

                if (j > 0)
                {
                    entityfishhook.setLureSpeed(j);
                }

                int k = EnchantmentHelper.getFishingLuckBonus(itemstack);

                if (k > 0)
                {
                    entityfishhook.setLuck(k);
                }

                worldIn.spawnEntity(entityfishhook);
            }

            playerIn.swingArm(handIn);
            playerIn.addStat(StatList.getObjectUseStats(this));
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
