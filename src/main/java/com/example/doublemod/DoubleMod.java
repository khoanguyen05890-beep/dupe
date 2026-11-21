package com.example.doublemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class DoubleMod implements ModInitializer {
    @Override
    public void onInitialize() {
        PayloadTypeRegistry.playC2S().register(DoublePayload.ID, DoublePayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(DoublePayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            ItemStack held = player.getMainHandStack();
            if (held.isEmpty()) return;

            player.getInventory().offerOrDrop(held.copy());
        });
    }
}
