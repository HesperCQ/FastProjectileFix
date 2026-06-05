package com.github.hespercq.fastprojectilefix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.world.phys.Vec3;

@Mixin(ClientboundSetEntityMotionPacket.class)
public class MixinClientboundSetEntityMotionPacket {

	@Inject(method = "<init>(ILnet/minecraft/world/phys/Vec3;)V", at = @At("HEAD"))
	private void test(int id, Vec3 deltaMovement, CallbackInfo ci) {
		System.out.println("MIXIN WORKS");
	}

	@Redirect(method = "<init>(ILnet/minecraft/world/phys/Vec3;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(DDD)D"))
	private double removeClamp(double value, double min, double max) {
		return value; // just return the original value, no clamping
	}

}
