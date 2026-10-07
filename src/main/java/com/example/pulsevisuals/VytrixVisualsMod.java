package com.example.pulsevisuals;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class VytrixVisualsMod implements ClientModInitializer {
    public static final String MOD_ID = "pulsevisuals";

    public static boolean customFogEnabled = true;
    public static boolean targetEspEnabled = true;
    public static boolean blockOverlayEnabled = true;

    private static KeyBinding configKeyBinding;

    @Override
    public void onInitializeClient() {
        configKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.pulsevisuals.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.pulsevisuals.general"
        ));

        WorldRenderEvents.END.register(context -> {
            if (targetEspEnabled) {
                TargetEspRenderer.render(context);
            }
            if (blockOverlayEnabled) {
                CustomBlockOverlay.render(context);
            }
        });
    }
}

