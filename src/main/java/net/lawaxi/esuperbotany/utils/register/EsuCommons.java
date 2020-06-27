package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.common.block.CopperBlock;
import net.lawaxi.esuperbotany.common.block.CopperOre;
import net.lawaxi.esuperbotany.common.item.ItemCopperIngot;
import net.lawaxi.esuperbotany.common.item.relic.Item1srod;
import net.lawaxi.esuperbotany.utils.names.FuctionalFlora;
import net.lawaxi.esuperbotany.utils.names.GeneratingFlora;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.BotaniaAPIClient;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EsuCommons {

    //Blocks
    public static Block FLORA;
    public static final List<String> floras = new ArrayList<>();

    public static Block COPPERORE;
    public static Block COPPERBLOCK;

    //Items
    public static Item COPPERINGOT;
    public static Item ONESROD;


    public static void init(){

        //花朵

        floras.addAll(Arrays.asList(new String[]{
                GeneratingFlora.LILY,
                GeneratingFlora.YANHUANG,
                FuctionalFlora.LOTUSPEONY
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

        COPPERORE = new CopperOre();
        COPPERBLOCK = new CopperBlock();


        //趣味道具
        ONESROD = new Item1srod();

    }
}
