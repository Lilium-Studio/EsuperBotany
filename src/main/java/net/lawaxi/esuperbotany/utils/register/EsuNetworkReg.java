package net.lawaxi.esuperbotany.utils.register;

import net.lawaxi.esuperbotany.network.EsuNetwork;
import net.lawaxi.esuperbotany.network.PacketXTHand;
import net.lawaxi.esuperbotany.network.utils.EsuPacket;
import net.lawaxi.esuperbotany.network.utils.EsuPacketHandler;
import net.minecraftforge.fml.relauncher.Side;

public class EsuNetworkReg {

    public static void init() {
        register(PacketXTHand.class, Side.SERVER);
    }


    //注册时填写收包端
    private static int id = 0;
    public static void register(Class<? extends EsuPacket> classs, Side side){
        EsuNetwork.CHANNEL.registerMessage( new EsuPacketHandler(), classs ,id++, side);
    }
}
