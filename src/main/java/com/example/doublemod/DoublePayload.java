package com.example.doublemod;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record DoublePayload() implements CustomPayload {
    public static final CustomPayload.Id<DoublePayload> ID =
            new CustomPayload.Id<>(Identifier.of("doublemod", "double_item"));
    public static final PacketCodec<PacketByteBuf, DoublePayload> CODEC =
            PacketCodec.unit(new DoublePayload());

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
