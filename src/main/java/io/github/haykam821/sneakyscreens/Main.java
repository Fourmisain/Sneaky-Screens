package io.github.haykam821.sneakyscreens;

import net.minecraft.client.MinecraftClient;

public class Main {
	public static boolean shouldSneakBeToggled(boolean sneakToggled) {
		MinecraftClient client = MinecraftClient.getInstance();
		boolean screenVisible = client.currentScreen != null;

		// prevent player from flying down when opening a screen
		boolean isFlying = client.player != null && client.player.getAbilities().flying;
		if (isFlying)
			return false;

		return sneakToggled || screenVisible;
	}
}