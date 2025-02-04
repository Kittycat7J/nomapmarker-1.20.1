package net.kittycat7j.mixin;

import net.kittycat7j.Nmmr;
import net.minecraft.client.render.MapRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.client.render.VertexConsumerProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(MapRenderer.class)
public class MapRendererMixin {
	@Inject(
			method = "draw",
			at = @At("HEAD")
	)
	public void onDraw(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int id, MapState state, boolean hidePlayerIcons, int light, CallbackInfo ci) {
		if (!Nmmr.config.mapRender) {
			// Use an iterator to remove frame icons
			Iterator<MapIcon> iterator = state.getIcons().iterator();
			while (iterator.hasNext()) {
				MapIcon icon = iterator.next();
				if (icon.getType() == MapIcon.Type.FRAME) {
					iterator.remove();
				}
			}
		}
	}
}

