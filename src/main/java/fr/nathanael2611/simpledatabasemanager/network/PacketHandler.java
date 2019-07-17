package fr.nathanael2611.simpledatabasemanager.network;

import erp.forge.core.ERPCoreForge;
import erp.forge.core.network.message.MessagePlayerMoneyHUD;
import fr.nathanael2611.simpledatabasemanager.SimpleDatabaseManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE;
    private static int nextId = 1;

    public static void init() {
        registerMessage(PacketSendClientPlayerData.Handler.class, PacketSendClientPlayerData.class, Side.CLIENT);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side) {
        INSTANCE.registerMessage(messageHandler, requestMessageType, nextId++, side);

    }

    static {
        INSTANCE = ERPCoreForge.instance.getPacketChannel();
    }
}