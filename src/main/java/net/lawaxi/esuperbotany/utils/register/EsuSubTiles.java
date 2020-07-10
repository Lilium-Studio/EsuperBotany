package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.block.flora.fuctional.Degausser;
import net.lawaxi.esuperbotany.block.flora.fuctional.Irrigator;
import net.lawaxi.esuperbotany.block.flora.fuctional.Joessr;
import net.lawaxi.esuperbotany.block.flora.fuctional.LotusPeony;
import net.lawaxi.esuperbotany.block.flora.generating.Lily;
import net.lawaxi.esuperbotany.block.flora.generating.YanHuang;
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

        //功能花
        registerSubTile(FuctionalFlora.LOTUSPEONY, LotusPeony.class);
        registerSubTile(FuctionalFlora.JOESSR, Joessr.class);
        registerSubTile(FuctionalFlora.IRRIGATOR, Irrigator.class);
        registerSubTile(FuctionalFlora.DEGAUSSER, Degausser.class);

    }


    private static void registerSubTile(String key, Class<? extends SubTileEntity> classs) {
        BotaniaAPI.registerSubTile(key, classs);

        try {
            BotaniaAPIClient.registerSubtileModel(key,
                    new ModelResourceLocation(new ResourceLocation("esuperbotany", key), "normal"),
                    new ModelResourceLocation(new ResourceLocation("esuperbotany", key), "inventory"));
        }catch (Error e){

        }

    }
}
