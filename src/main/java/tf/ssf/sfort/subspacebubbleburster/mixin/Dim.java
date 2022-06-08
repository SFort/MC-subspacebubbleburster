package tf.ssf.sfort.subspacebubbleburster.mixin;

import net.minecraft.world.dimension.DimensionTypeRegistrar;
import net.minecraft.world.dimension.DimensionTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(DimensionTypeRegistrar.class)
public abstract class Dim {

	@ModifyArgs(method= "initAndGetDefault", at=@At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/DimensionType;<init>(Ljava/util/OptionalLong;ZZZZDZZIIILnet/minecraft/tag/TagKey;Lnet/minecraft/util/Identifier;FLnet/minecraft/world/dimension/DimensionType$MonsterSettings;)V"))
	private static void changeScale(Args args){
		if (DimensionTypes.THE_NETHER_ID.equals(args.get(12))){
			args.set(5, Config.netherScale);
		}
	}

}
