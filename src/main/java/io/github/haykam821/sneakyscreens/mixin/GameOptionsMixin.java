package io.github.haykam821.sneakyscreens.mixin;

import io.github.haykam821.sneakyscreens.Main;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.MinecraftClient;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
	@Shadow public boolean sneakToggled;

	@Dynamic("KeyBinding keySneak lambda")
	@Inject(at = @At("RETURN"), method = "method_23487", remap = false, cancellable = true)
    private void load(CallbackInfoReturnable<Boolean> info) {
		info.setReturnValue(Main.toggleGetter(this.sneakToggled, MinecraftClient.getInstance()));
	}
}