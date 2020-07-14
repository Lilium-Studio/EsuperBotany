package net.lawaxi.esuperbotany.network2.utils;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class EsuPacketHandler implements IMessageHandler<EsuPacket, IMessage> {

    @Override
    public IMessage onMessage(EsuPacket message, MessageContext ctx) {

        if (ctx.side == Side.SERVER) {
            return message.handleServer(ctx.getServerHandler());
        } else {
            return message.handleClient(ctx.getClientHandler());
        }
    }
}
