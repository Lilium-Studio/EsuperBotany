package net.lawaxi.esuperbotany.common.item.relic;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.core.helper.ItemNBTHelper;

import java.util.UUID;

public class ItemRelic2 extends Item implements IRelic {

    public ItemRelic2(String name) {

        setUnlocalizedName(name);
        ForgeRegistries.ITEMS.register(this.setRegistryName(name));
        ModelLoader.setCustomModelResourceLocation(this,0,new ModelResourceLocation(name, "inventory"));
    }

    private static final String TAG ="soulbindUUID";

    @Override
    public boolean hasUUID(ItemStack itemStack) {
        return false;
    }

    @Override
    public void bindToUUID(UUID uuid, ItemStack stack) {
        ItemNBTHelper.setString(stack, TAG, uuid.toString());
    }

    @Override
    public UUID getSoulbindUUID(ItemStack stack) {
        if (ItemNBTHelper.verifyExistance(stack, TAG)) {
            try {
                return UUID.fromString(ItemNBTHelper.getString(stack, TAG, ""));
            } catch (IllegalArgumentException var3) {
                ItemNBTHelper.removeEntry(stack, TAG);
            }
        }

        return null;
    }

}
