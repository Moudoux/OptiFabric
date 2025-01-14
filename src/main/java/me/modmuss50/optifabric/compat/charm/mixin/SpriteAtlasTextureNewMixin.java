package me.modmuss50.optifabric.compat.charm.mixin;

import java.util.Set;
import java.util.stream.Stream;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.texture.SpriteAtlasTexture.Data;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import me.modmuss50.optifabric.compat.InterceptingMixin;
import me.modmuss50.optifabric.compat.PlacatingSurrogate;
import me.modmuss50.optifabric.compat.Shim;

@Mixin(SpriteAtlasTexture.class)
@InterceptingMixin("svenhjol/charm/mixin/event/StitchTextureMixin")
class SpriteAtlasTextureNewMixin {
	@PlacatingSurrogate
	private void hookStitch(ResourceManager resourceManager, Stream<Identifier> idStream, Profiler profiler, int mipmapLevel, CallbackInfoReturnable<Data> call, int mipmapLevels) {
	}

	@Inject(method = "stitch", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
	private void hookStitch(ResourceManager resourceManager, Stream<Identifier> idStream, Profiler profiler, int mipmapLevel, CallbackInfoReturnable<Data> call, int mipmapLevels, Set<Identifier> set) {
		hookStitch(resourceManager, idStream, profiler, mipmapLevels, call, set);
	}

	@Shim
	private native void hookStitch(ResourceManager resourceManager, Stream<Identifier> idStream, Profiler profiler, int mipmapLevel, CallbackInfoReturnable<Data> call, Set<Identifier> set);
}