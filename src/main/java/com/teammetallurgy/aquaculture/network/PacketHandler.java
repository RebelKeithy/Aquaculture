package com.teammetallurgy.aquaculture.network;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.network.packet.PacketSyncStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler {
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Aquaculture.MOD_ID, "channel"))
            .clientAcceptedVersions(v -> true)
            .serverAcceptedVersions(v -> true)
            .networkProtocolVersion(() -> "AQUACULTURE2")
            .simpleChannel();

    public static void registerPackets() {
        CHANNEL.registerMessage(0, PacketSyncStack.class, PacketSyncStack::encode, PacketSyncStack::decode, PacketSyncStack.Handler::handle);
    }

    public static void sendToClient(Object packet, ServerPlayerEntity playerServer) {
        CHANNEL.sendTo(packet, playerServer.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
    }
}