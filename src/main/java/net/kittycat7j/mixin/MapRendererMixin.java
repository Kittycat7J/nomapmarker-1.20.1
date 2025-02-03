package net.kittycat7j.mixin;

import net.kittycat7j.Nmmr;
import net.minecraft.client.render.MapRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.map.MapState;
import net.minecraft.client.render.VertexConsumerProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MapRenderer.class)
public class MapRendererMixin {
	@Inject(method = "draw", at = @At("HEAD"), cancellable = true)
	private void onDraw(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int id, MapState state, boolean hidePlayerIcons, int light, CallbackInfo ci) {
		if (Nmmr.config.mapRender) {
			// Cancel the draw method to disable map icon rendering
			ci.cancel();
		}
	}
}