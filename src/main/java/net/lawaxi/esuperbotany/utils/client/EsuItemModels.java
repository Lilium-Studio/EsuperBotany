package net.lawaxi.esuperbotany.utils.client;

import net.lawaxi.esuperbotany.item.ItemCosmetic;
import net.lawaxi.esuperbotany.item.ItemFood;
import net.lawaxi.esuperbotany.item.ItemLootBag;
import net.lawaxi.esuperbotany.item.ItemResource;
import net.lawaxi.esuperbotany.utils.register.EsuCommons;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class EsuItemModels {

    public static void init(){


        for (Item a : EsuCommons.items) {
            if(!a.getHasSubtypes())
                ModelLoader.setCustomModelResourceLocation(a, 0, new ModelResourceLocation(a.getRegistryName(), "inventory"));
        }

        for(int i = 0; i< ItemResource.names.length; i++){
            ModelLoader.setCustomModelResourceLocation(EsuCommons.RESOURCE,i,new ModelResourceLocation("esuperbotany:"+ItemResource.names[i], "inventory"));
        }

        for(int i = 0; i< ItemCosmetic.names.length; i++){
            ModelLoader.setCustomModelResourceLocation(EsuCommons.COSMETIC,i,new ModelResourceLocation("esuperbotany:"+ItemCosmetic.names[i], "inventory"));
        }

        for(int i = 0; i< ItemLootBag.names.length; i++){
            ModelLoader.setCustomModelResourceLocation(EsuCommons.LOOTBAG,i,new ModelResourceLocation("esuperbotany:"+ItemLootBag.names[i], "inventory"));
        }

        for(int i = 0; i< ItemFood.names.length; i++){

            switch (i){
                default:
                    ModelLoader.setCustomModelResourceLocation(EsuCommons.FOOD,i,new ModelResourceLocation("esuperbotany:"+ItemFood.names[i], "inventory"));
                    break;
                case 1:
                case 2:
                case 3: {
                    ModelLoader.setCustomModelResourceLocation(EsuCommons.FOOD, i, new ModelResourceLocation("esuperbotany:liquid", "inventory"));
                    break;
                }
            }
        }


    }
}
