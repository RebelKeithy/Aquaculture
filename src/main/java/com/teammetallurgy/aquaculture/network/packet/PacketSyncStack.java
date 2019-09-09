package com.teammetallurgy.aquaculture.network.packet;

import com.teammetallurgy.aquaculture.network.PacketHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkEvent;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class PacketSyncStack {
    private final ItemStack stack;

    public PacketSyncStack(@Nonnull ItemStack stack) {
        this.stack = stack;
    }

    public static void encode(PacketSyncStack packet, PacketBuffer buf) {
        buf.writeItemStack(packet.stack);
    }

    public static PacketSyncStack decode(PacketBuffer buf) {
        return new PacketSyncStack(buf.readItemStack());
    }

    public static class Handler {
        public static void handle(PacketSyncStack message, Supplier<NetworkEvent.Context> ctx) {
            ServerPlayerEntity playerMP = ctx.get().getSender();
            if (playerMP != null && !(playerMP instanceof FakePlayer)) {
                ctx.get().enqueueWork(() -> PacketHandler.sendToClient(message, playerMP));
                ctx.get().setPacketHandled(true);
            }
        }
    }
}