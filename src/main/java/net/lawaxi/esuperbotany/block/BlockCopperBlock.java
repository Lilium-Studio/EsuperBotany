package net.lawaxi.esuperbotany.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class BlockCopperBlock extends CommonBlock {

    public BlockCopperBlock() {

        super("esuperbotany:copper_block",Material.ROCK);

        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe",2);
        this.setHardness(10.0F);

        OreDictionary.registerOre("blockCopper", this);
    }
}
