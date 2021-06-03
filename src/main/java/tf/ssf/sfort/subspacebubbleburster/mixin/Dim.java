package tf.ssf.sfort.subspacebubbleburster.mixin;

import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = DimensionType.class, priority = 1612)
public abstract class Dim {

	@Shadow @Final protected static DimensionType THE_NETHER;

	@Inject(method= "method_31109(Lnet/minecraft/world/dimension/DimensionType;Lnet/minecraft/world/dimension/DimensionType;)D", at=@At("HEAD"), cancellable = true)
	private static void change_scale(DimensionType dimensionType, DimensionType dimensionType2, CallbackInfoReturnable<Double> cir){
		if(dimensionType.equals(THE_NETHER)) cir.setReturnValue(Config.netherScale / dimensionType2.getCoordinateScale());
		else if(dimensionType2.equals(THE_NETHER)) cir.setReturnValue(dimensionType.getCoordinateScale() / Config.netherScale);
	}
}
