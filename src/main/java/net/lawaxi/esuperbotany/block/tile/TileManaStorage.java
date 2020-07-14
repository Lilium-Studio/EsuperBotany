package net.lawaxi.esuperbotany.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import javax.annotation.Nonnull;

public class TileManaStorage extends CommonTile {

    public int mana;

    @Nonnull
    @Override
    public NBTTagCompound func_189515_b(NBTTagCompound par1nbtTagCompound) {
        NBTTagCompound ret = super.func_189515_b(par1nbtTagCompound);
        ret.setInteger("mana", this.mana);
        return ret;
    }

    @Override
    public void func_145839_a(NBTTagCompound par1nbtTagCompound) {
        super.func_145839_a(par1nbtTagCompound);
        this.mana = par1nbtTagCompound.getInteger("mana");
    }
}
