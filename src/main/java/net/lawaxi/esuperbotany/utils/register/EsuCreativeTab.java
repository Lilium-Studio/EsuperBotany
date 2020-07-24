package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.item.ItemCosmetic;
import net.lawaxi.esuperbotany.item.ItemFood;
import net.lawaxi.esuperbotany.item.ItemLootBag;
import net.lawaxi.esuperbotany.item.ItemResource;
import net.lawaxi.esuperbotany.utils.names.GeneratingFlora;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import java.util.ArrayList;
import java.util.Arrays;

public class EsuCreativeTab {

    public static CreativeTabs TAB;
    public static final ArrayList<String> items = new ArrayList<>();


    public static void init(){

        addItems();

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


                for(String item:items){

                    String a1[] = item.split(":");
                    a1[0] = "esuperbotany:"+a1[0];

                    if(a1.length==1)
                        list.add(new ItemStack(Item.getByNameOrId(a1[0])));

                    else if (a1.length==2)
                        list.add(new ItemStack(Item.getByNameOrId(a1[0]),1,Integer.valueOf(a1[1])));

                    else{
                        ItemStack stack;
                        if(a1[1].equalsIgnoreCase(""))
                            stack = new ItemStack(Item.getByNameOrId(a1[0]));
                        else
                            stack = new ItemStack(Item.getByNameOrId(a1[0]),1,Integer.valueOf(a1[1]));

                        String a2[] = a1[2].split("--");
                        for(String nbt:a2){
                            String a3[] = nbt.split("-");
                            ItemNBTHelper.setInt(stack,a3[0],Integer.valueOf(a3[1]));
                        }
                        list.add(stack);

                    }

                }


            }
        };
        TAB.setNoTitle()
            .setBackgroundImageName("esuper.png");
    }

    private static void addItems(){

        // id:meta:nbt
        for(int i=0;i<ItemResource.names.length;i++){
            items.add("resource:"+i);
        }

        items.addAll(Arrays.asList(new String[]{
                "manaemerald_block",
                "manastorage",
                "manastorage::mana-"+Integer.MAX_VALUE,

        }));

        for(int i = 0; i< ItemFood.names.length; i++){
            items.add("food:"+i);
        }

        items.addAll(Arrays.asList(new String[]{
                "dice"
        }));

        for(int i=0;i<ItemLootBag.names.length;i++){
            items.add("lootbag:"+i);
        }

        for(int i=0;i<ItemCosmetic.names.length;i++){
            items.add("cosmetic:"+i);
        }

        items.addAll(Arrays.asList(new String[]{
                "copper_ingot",
                "copper_ore",
                "copper_block",
                "inftorch",
                "voidwalker",
                "+1srod",
                "oldeaterrod",
                "expellorod",
                "manabow",
                "manabow::cost-0",
                "xtHelmet",
                "xtChestplate",
                "xtLeggings",
                "xtBoots",
                "bhsfuniform",
                "bhsfuniform::custom-0"

        }));

        items.addAll(Arrays.asList(new String[]{
                "record_bhsf"
        }));
    }
}
