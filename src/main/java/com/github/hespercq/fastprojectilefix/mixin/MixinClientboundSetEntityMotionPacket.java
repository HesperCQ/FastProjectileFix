package com.github.hespercq.fastprojectilefix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;

@Mixin(ClientboundSetEntityMotionPacket.class)
public class MixinClientboundSetEntityMotionPacket {

	@Redirect(method = "<init>(ILnet/minecraft/world/phys/Vec3;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(DDD)D"))
	private double removeClamp(double value, double min, double max) {
		return value; // just return the original value, no clamping
	}

}
