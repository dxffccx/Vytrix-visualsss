package com.example.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class VytrixVisualsMod implements ClientModInitializer {
    public static boolean customFogEnabled = true;

    @Override
    public void onInitializeClient() {
        WorldRenderEvents.END.register(CustomBlockOverlay::render);
        WorldRenderEvents.END.register(TargetEspRenderer::render);
    }
}
