package net.caffeinemc.mods.sodium.mixin.debug.checks.threading;

import com.mojang.blaze3d.vertex.VertexBuffer;
import net.caffeinemc.mods.sodium.client.render.util.RenderAsserts;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VertexBuffer.class)
public class MixinVertexBuffer {
    @Redirect(method = {
            "drawWithShader(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lnet/minecraft/client/renderer/ShaderInstance;)V",
    }, at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;isOnRenderThread()Z"))
    private boolean validateCurrentThread$draw() {
        return RenderAsserts.validateCurrentThread();
    }
}
