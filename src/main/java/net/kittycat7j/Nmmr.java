package net.kittycat7j;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;
import net.minecraft.client.util.InputUtil;

public class Nmmr implements ModInitializer {
	public static ModConfig config;
	private static KeyBinding toggleMapRenderKey;

	@Override
	public void onInitialize() {
		config = new ModConfig();

		// Register the keybinding
		toggleMapRenderKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"ToggleMapRender",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_M,
				"NMMR"
		));

		// Register the client tick event to handle key input
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (toggleMapRenderKey.wasPressed()) {
				toggleMapRender();
			}
		});
	}

	public static void toggleMapRender() {
		config.mapRender = !config.mapRender;
//		config.save(); // Save the configuration
		sendMessageToChat("Map render toggled: " + (config.mapRender ? "ON" : "OFF"));
	}

	private static void sendMessageToChat(String message) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player != null) {
			client.player.sendMessage(Text.literal(message).formatted(Formatting.DARK_PURPLE), false);
		}
	}
}