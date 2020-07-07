package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.item.ItemCosmetic;
import net.lawaxi.esuperbotany.item.ItemLootBag;
import net.lawaxi.esuperbotany.item.ItemResource;
import net.lawaxi.esuperbotany.utils.names.GeneratingFlora;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class EsuCreativeTab {

    public static CreativeTabs TAB;


    public static void init(){

        //构建
        TAB = new CreativeTabs("esuperbotany") {

            @Override
            public ItemStack getTabIconItem(){
                return ItemBlockSpecialFlower.ofType(GeneratingFlora.LILY);
            }

            @Override
            public boolean hasSearchBar(){
                return true;
            }

            @Override
            public void displayAllRelevantItems(NonNullList<ItemStack> list) {

                EsuCommons.FLORA.getSubBlocks(this,list);

                for(int i = 0; i< ItemResource.names.length; i++){
                    list.add(new ItemStack(EsuCommons.RESOURCE,1,i));
                }

                for(int i = 0; i< ItemLootBag.names.length; i++){
                    list.add(new ItemStack(EsuCommons.LOOTBAG,1,i));
                }

                for(int i = 0; i< ItemCosmetic.names.length; i++){
                    list.add(new ItemStack(EsuCommons.COSMETIC,1,i));
                }

                ItemStack a = new ItemStack(EsuCommons.MANASTORAGE);
                ItemNBTHelper.setInt(a,"mana",Integer.MAX_VALUE);
                list.add(a);

                super.displayAllRelevantItems(list);
            }
        };
        TAB.setNoTitle();
        TAB.setBackgroundImageName("esuper.png");

        additem(EsuCommons.COPPERINGOT);
        addblock(EsuCommons.COPPERORE);
        addblock(EsuCommons.COPPERBLOCK);
        addblock(EsuCommons.MANAEMERALDBLOCK);
        addblock(EsuCommons.MANASTORAGE);

        additem(EsuCommons.ONESROD);
        additem(EsuCommons.OLDEATER);
        additem(EsuCommons.EXPELLOROD);
        additem(EsuCommons.MANABOW);
        additem(EsuCommons.INFTORCH);

        /*
        additem(Item.getByNameOrId("esuperbotany:stinkyHelmet"));
        additem(Item.getByNameOrId("esuperbotany:stinkyChestplate"));
        additem(Item.getByNameOrId("esuperbotany:stinkyLeggings"));
        additem(Item.getByNameOrId("esuperbotany:stinkyBoots"));*/
    }


    private static void additem(Item a){
        a.setCreativeTab(TAB);
    }

    private static void addblock(Block b){b.setCreativeTab(TAB);}
}
