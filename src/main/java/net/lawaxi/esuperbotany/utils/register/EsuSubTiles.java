package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.block.subtiles.fuctional.*;
import net.lawaxi.esuperbotany.block.subtiles.generating.Lily;
import net.lawaxi.esuperbotany.block.subtiles.generating.RaffRafflowsia;
import net.lawaxi.esuperbotany.block.subtiles.generating.YanHuang;
import net.lawaxi.esuperbotany.utils.names.FuctionalFlora;
import net.lawaxi.esuperbotany.utils.names.GeneratingFlora;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.BotaniaAPIClient;
import vazkii.botania.api.subtile.SubTileEntity;

public class EsuSubTiles {

    public static void init(){

        //产能花
        registerSubTile(GeneratingFlora.LILY, Lily.class);
        registerSubTile(GeneratingFlora.YANHUANG, YanHuang.class);
        registerSubTile(GeneratingFlora.RAFFRAFFLOWSIA, RaffRafflowsia.class);

        //功能花
        registerSubTile(FuctionalFlora.LOTUSPEONY, LotusPeony.class);
        registerSubTile(FuctionalFlora.JOESSR, Joessr.class);
        registerSubTile(FuctionalFlora.IRRIGATOR, Irrigator.class);
        registerSubTile(FuctionalFlora.DEGAUSSER, Degausser.class);
        registerSubTile(FuctionalFlora.CUCURBIT, Cucurbit.class);

    }


    private static void registerSubTile(String key, Class<? extends SubTileEntity> classs) {
        BotaniaAPI.registerSubTile(key, classs);


        try {

            switch (key) {
                case GeneratingFlora.RAFFRAFFLOWSIA: {
                    BotaniaAPIClient.registerSubtileModel(key,
                            new ModelResourceLocation(new ResourceLocation("botania", "rafflowsia"), "normal"),
                            new ModelResourceLocation(new ResourceLocation("botania", "rafflowsia"), "inventory"));
                    break;
                }
                default: {
                    BotaniaAPIClient.registerSubtileModel(key,
                            new ModelResourceLocation(new ResourceLocation("esuperbotany", key), "normal"),
                            new ModelResourceLocation(new ResourceLocation("esuperbotany", key), "inventory"));
                    break;
                }
            }


        }catch (Error e){

        }

    }
}
