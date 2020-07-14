package net.lawaxi.esuperbotany.network2;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class EsuNetwork {

    public static final String NAME = "ESUPER";
    public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(NAME);


    //参考教程
    //https://harbinger.covertdragon.team/chapter-07/forge-extension/simple-network-wrapper.html

    // 向某个维度发包（服务器到客户端）
    public static void sendMessageToDim(IMessage msg, int dim) {
        CHANNEL.sendToDimension(msg, dim);
    }

    // 向某个维度的某个点发包（服务器到客户端）
    public static void sendMessageAroundPos(IMessage msg, int dim, BlockPos pos) {
        // TargetPoint的构造器为：
        // 维度id x坐标 y坐标 z坐标 覆盖范围
        // 其中，覆盖范围指接受此更新数据包的坐标的范围
        CHANNEL.sendToAllAround(msg, new NetworkRegistry.TargetPoint(dim, pos.getX(), pos.getY(), pos.getZ(), 2.0D));
    }

    // 向某个玩家发包（服务器到客户端）
    public static void sendMessageToPlayer(IMessage msg, EntityPlayerMP player) {
        CHANNEL.sendTo(msg, player);
    }

    // 向所有人发包（服务器到客户端）
    public static void sendMessageToAll(IMessage msg) {
        CHANNEL.sendToAll(msg);
    }

    // 向服务器发包（客户端到服务器）
    public static void sendMessageToServer(IMessage msg) {
        CHANNEL.sendToServer(msg);
    }

}
