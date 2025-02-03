package net.kittycat7j;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.util.InputUtil;

public class Nmmr implements ModInitializer {
	public static final String MOD_ID = "nmmr";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ModConfig config;
	private static KeyBinding toggleMapRenderKey;

	@Override
	public void onInitialize() {
		LOGGER.info("NMMR mod initialized!");
		config = new ModConfig();

		// Register the keybinding
		toggleMapRenderKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.nmmr.toggleMapRender",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_M,
				"category.nmmr"
		));

		// Register the client tick event to handle key input
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (toggleMapRenderKey.wasPressed()) {
				config.mapRender = !config.mapRender;
				config.save(); // Save the configuration
				LOGGER.info("Map render toggled: {}", config.mapRender);
				sendMessageToChat("Map render toggled: " + (config.mapRender ? "ON" : "OFF"));
			}
		});
	}

	private void sendMessageToChat(String message) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player != null) {
			client.player.sendMessage(Text.literal(message).formatted(Formatting.YELLOW), false);
		}
	}
}