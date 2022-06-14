package io.github.haykam821.sneakyscreens.mixin;

import io.github.haykam821.sneakyscreens.Main;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.BooleanSupplier;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
	// For KeyBinding sneakKey, inside new StickyKeyBinding(), replace sneakToggled::getValue with a custom BooleanSupplier
	@ModifyArg(method = "<init>(Lnet/minecraft/client/MinecraftClient;Ljava/io/File;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/option/StickyKeyBinding;<init>(Ljava/lang/String;ILjava/lang/String;Ljava/util/function/BooleanSupplier;)V",
					ordinal = 0 // first new StickyKeyBinding()
			)
	)
    private BooleanSupplier load(BooleanSupplier sneakToggled) {
		return () -> Main.shouldSneakBeToggled(sneakToggled.getAsBoolean());
	}
}