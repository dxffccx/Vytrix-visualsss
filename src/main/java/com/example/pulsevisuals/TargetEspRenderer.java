package com.example.pulsevisuals;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class TargetEspRenderer {

    public static void render(WorldRenderContext context) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null || client.player == null) return;

        MatrixStack matrices = context.matrixStack();
        Vec3d cameraPos = context.camera().getPos();

        for (Object entityObj : client.world.getEntities()) {
            if (entityObj instanceof LivingEntity entity && entity != client.player) {
                double x = entity.getX() - cameraPos.x;
                double y = entity.getY() - cameraPos.y;
                double z = entity.getZ() - cameraPos.z;

                matrices.push();
                matrices.translate(x, y, z);
                
                drawEntityBox(matrices, entity.getWidth(), entity.getHeight());

                matrices.pop();
            }
        }
    }

    private static void drawEntityBox(MatrixStack matrices, float width, float height) {
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        RenderSystem.lineWidth(2.0f);
    }
}

