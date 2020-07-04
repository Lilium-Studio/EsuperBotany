package net.lawaxi.esuperbotany.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockManaEmeraldBlock extends CommonBlock {

    public BlockManaEmeraldBlock() {

        super("esuperbotany:manaemerald_block",Material.ROCK);

        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe",2);
        this.setHardness(10.0F);
    }
}
