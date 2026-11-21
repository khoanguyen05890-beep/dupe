package com.example.doublemod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class DoubleModClient implements ClientModInitializer {
    private static KeyBinding doubleKey;

    @Override
    public void onInitializeClient() {
        doubleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.doublemod.double",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                KeyBinding.Category.create(Identifier.of("doublemod", "main"))
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (doubleKey.wasPressed()) {
                if (client.player != null && client.currentScreen == null) {
                    ClientPlayNetworking.send(new DoublePayload());
                }
            }
        });
    }
}
