package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.block.BlockCopperBlock;
import net.lawaxi.esuperbotany.block.BlockCopperOre;
import net.lawaxi.esuperbotany.block.BlockManaEmeraldBlock;
import net.lawaxi.esuperbotany.block.BlockManaStorage;
import net.lawaxi.esuperbotany.item.*;
import net.lawaxi.esuperbotany.item.equipment.ArmorBHSFUniform;
import net.lawaxi.esuperbotany.item.equipment.ArmorXT;
import net.lawaxi.esuperbotany.item.record.ItemRecordBHSF;
import net.lawaxi.esuperbotany.item.relic.Item1srod;
import net.lawaxi.esuperbotany.item.relic.ItemExpelloRod;
import net.lawaxi.esuperbotany.item.relic.ItemManaBow;
import net.lawaxi.esuperbotany.item.relic.ItemOldEater;
import net.lawaxi.esuperbotany.item.util.CommonItemBowRelic;
import net.lawaxi.esuperbotany.item.util.CommonItemRecord;
import net.lawaxi.esuperbotany.item.util.CommonItemRelic;
import net.lawaxi.esuperbotany.utils.names.FuctionalFlora;
import net.lawaxi.esuperbotany.utils.names.GeneratingFlora;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
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
    public static ItemFood FOOD;

    public static CommonItemRecord RECORDBHSF;

    public static Block MANAEMERALDBLOCK;
    public static BlockManaStorage MANASTORAGE;
    public static Item INFTORCH;
    public static Item VOIDWALKER;

    public static CommonItemRelic ONESROD;
    public static CommonItemRelic OLDEATER;
    public static CommonItemRelic EXPELLOROD;
    public static CommonItemBowRelic MANABOW;

    public static ArmorXT XT0;
    public static ArmorXT XT1;
    public static ArmorXT XT2;
    public static ArmorXT XT3;
    public static ArmorBHSFUniform BHSFUNIFORM;

    public static final HashSet<Item> items = new HashSet<>();

    public static void init(){

        /*

        由于注册了tileEntity后发现活塞推不动的情况 因此删掉
        GameRegistry.registerTileEntity(TileManaStorage.class,new ResourceLocation("esuperbotany","manastorage"));
           */


        //花朵

        floras.addAll(Arrays.asList(new String[]{
                GeneratingFlora.LILY,
                GeneratingFlora.YANHUANG,
                FuctionalFlora.LOTUSPEONY,
                FuctionalFlora.IRRIGATOR,
                FuctionalFlora.JOESSR,
                FuctionalFlora.DEGAUSSER,
                FuctionalFlora.CUCURBIT
        }));

        FLORA = new BlockSpecialFlower(){

            @Override
            public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
                for(String flora:floras){
                    items.add(ItemBlockSpecialFlower.ofType(flora));
                }
            }
        };

        COPPERINGOT = new ItemCopperIngot();
        COPPERORE = new BlockCopperOre();
        COPPERBLOCK = new BlockCopperBlock();
        MANAEMERALDBLOCK = new BlockManaEmeraldBlock();
        MANASTORAGE = new BlockManaStorage();

        RECORDBHSF = new ItemRecordBHSF();

        RESOURCE = new ItemResource();
        COSMETIC = new ItemCosmetic();
        LOOTBAG = new ItemLootBag();
        FOOD = new ItemFood();
        INFTORCH = new ItemInfTorch();
        VOIDWALKER = new ItemVoidWalker();

        ONESROD = new Item1srod();
        OLDEATER = new ItemOldEater();
        EXPELLOROD = new ItemExpelloRod();
        MANABOW = new ItemManaBow();

        XT0 = new ArmorXT(EntityEquipmentSlot.HEAD);
        XT1 = new ArmorXT(EntityEquipmentSlot.CHEST);
        XT2 = new ArmorXT(EntityEquipmentSlot.LEGS);
        XT3 = new ArmorXT(EntityEquipmentSlot.FEET);

        BHSFUNIFORM = new ArmorBHSFUniform();

    }


}
