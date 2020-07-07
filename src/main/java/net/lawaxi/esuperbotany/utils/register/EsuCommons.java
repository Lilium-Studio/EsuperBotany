package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.block.BlockCopperBlock;
import net.lawaxi.esuperbotany.block.BlockCopperOre;
import net.lawaxi.esuperbotany.block.BlockManaEmeraldBlock;
import net.lawaxi.esuperbotany.block.BlockManaStorage;
import net.lawaxi.esuperbotany.item.*;
import net.lawaxi.esuperbotany.item.relic.CommonItemRelic;
import net.lawaxi.esuperbotany.item.relic.Item1srod;
import net.lawaxi.esuperbotany.item.relic.ItemExpelloRod;
import net.lawaxi.esuperbotany.item.relic.ItemOldEater;
import net.lawaxi.esuperbotany.utils.names.FuctionalFlora;
import net.lawaxi.esuperbotany.utils.names.GeneratingFlora;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EsuCommons {

    //Blocks
    public static Block FLORA;
    public static final List<String> floras = new ArrayList<>();

    public static Block COPPERORE;
    public static Block COPPERBLOCK;

    //Items
    public static Item COPPERINGOT;
    public static ItemResource RESOURCE;
    public static ItemCosmetic COSMETIC;
    public static ItemLootBag LOOTBAG;

    public static Block MANAEMERALDBLOCK;
    public static Block MANASTORAGE;
    public static CommonItemRelic ONESROD;
    public static CommonItemRelic OLDEATER;
    public static CommonItemRelic EXPELLOROD;
    public static Item INFTORCH;

    public static final HashSet<Item> items = new HashSet<>();

    public static void init(){

        //花朵

        floras.addAll(Arrays.asList(new String[]{
                GeneratingFlora.LILY,
                GeneratingFlora.YANHUANG,
                FuctionalFlora.LOTUSPEONY,
                FuctionalFlora.IRRIGATOR,
                FuctionalFlora.JOESSR
        }));

        FLORA = new BlockSpecialFlower(){

            @Override
            public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
                for(String flora:floras){
                    items.add(ItemBlockSpecialFlower.ofType(flora));
                }
            }
        };


        //铜
        COPPERINGOT = new ItemCopperIngot();

        COPPERORE = new BlockCopperOre();
        COPPERBLOCK = new BlockCopperBlock();
        MANAEMERALDBLOCK = new BlockManaEmeraldBlock();
        MANASTORAGE = new BlockManaStorage();

        //魔法材料
        RESOURCE = new ItemResource();
        COSMETIC = new ItemCosmetic();
        LOOTBAG = new ItemLootBag();

        //趣味道具
        ONESROD = new Item1srod();
        OLDEATER = new ItemOldEater();
        EXPELLOROD = new ItemExpelloRod();
        INFTORCH = new ItemInfTorch();

        //ArmorStinky.build();
        //new debug();
    }


}
