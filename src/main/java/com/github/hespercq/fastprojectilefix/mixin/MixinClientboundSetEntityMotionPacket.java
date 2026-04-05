package com.github.hespercq.fastprojectilefix.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.phys.Vec3;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;

@Mixin(ClientboundSetEntityMotionPacket.class)
public class MixinClientboundSetEntityMotionPacket {
	@Shadow
	@Final
	@Mutable
	private int id;

	@Shadow
	@Final
	@Mutable
	private int xa;

	@Shadow
	@Final
	@Mutable
	private int ya;

	@Shadow
	@Final
	@Mutable
	private int za;

	// Constructor from Vector
	@Inject(method = "<init>(ILnet/minecraft/world/phys/Vec3;)V", at = @At(value = "RETURN"), cancellable = true)
	public void ClientboundSetEntityMotionPacket(int entityId, Vec3 motionVector, CallbackInfo ci) {
		this.id = entityId;
		this.xa = (int) (motionVector.x * 8000.0D);
		this.ya = (int) (motionVector.y * 8000.0D);
		this.za = (int) (motionVector.z * 8000.0D);
	}

	/*

	// Constructor from Packet => Redirect buf.readShort -> buf.readInt
	@Inject(method = "<init>(Lnet/minecraft/network/FriendlyByteBuf;)V", at = @At(value = "RETURN"), cancellable = true)
	public void redirect_constructor_FriendlyByteBuf_readShort(FriendlyByteBuf buf, CallbackInfo ci) {
		this.id = buf.readVarInt();
		this.xa = buf.readInt();
		this.ya = buf.readInt();
		this.za = buf.readInt();
	}

	// write => Redirect buf.writeShort -> buf.writeInt
	@Redirect(
		method = "write",
		at = @At(value = "INVOKE", target = "Lio/netty/buffer/ByteBuf;writeShort(I)Lnet/minecraft/network/FriendlyByteBuf;"))
	private ByteBuf redirect_write_writeShort(FriendlyByteBuf buf, int value) {
		return buf.writeInt(value);
	}

	 */
}
