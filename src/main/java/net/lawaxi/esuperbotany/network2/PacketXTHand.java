package net.lawaxi.esuperbotany.network2;

import net.lawaxi.esuperbotany.item.equipment.ArmorXT;
import net.lawaxi.esuperbotany.network2.utils.EsuPacket;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;

public class PacketXTHand extends EsuPacket {

    @Override
    public void handleClientSafe(NetHandlerPlayClient netHandler) {

    }

    @Override
    public void handleServerSafe(NetHandlerPlayServer netHandler) {

        EntityPlayerMP player = netHandler.player;
        player.mcServer.addScheduledTask(() -> ArmorXT.shoot(player));
    }
}
