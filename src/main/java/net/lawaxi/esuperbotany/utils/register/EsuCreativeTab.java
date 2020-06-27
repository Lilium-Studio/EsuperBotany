package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.utils.names.GeneratingFlora;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
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
                super.displayAllRelevantItems(list);
            }
        };
        TAB.setNoTitle();
        TAB.setBackgroundImageName("esuper.png");

        additem(EsuCommons.COPPERINGOT);
        addblock(EsuCommons.COPPERORE);
        addblock(EsuCommons.COPPERBLOCK);
        additem(EsuCommons.ONESROD);
    }


    private static void additem(Item a){
        a.setCreativeTab(TAB);
    }

    private static void addblock(Block b){b.setCreativeTab(TAB);}
}
