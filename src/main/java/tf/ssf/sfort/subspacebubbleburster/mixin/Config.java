package tf.ssf.sfort.subspacebubbleburster.mixin;

import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Config implements IMixinConfigPlugin {
    private static final String mod = "tf.ssf.sfort.subspacebubbleburster";
    public static Logger LOGGER = LogManager.getLogger();
    public static double netherScale = 8.0;

    @Override
    public void onLoad(String mixinPackage) {
        // Configs
        File confFile = new File(
                FabricLoader.getInstance().getConfigDirectory().toString(),
                "SubspaceBubbleBurster.conf"
        );
        try {
            confFile.createNewFile();
            List<String> la = Files.readAllLines(confFile.toPath());
            List<String> defaultDesc = Arrays.asList(
                    "^-Nether scale [8.0]"
            );
            String[] ls = la.toArray(new String[Math.max(la.size(), defaultDesc.size() * 2)|1]);
            final int hash = Arrays.hashCode(ls);
            for (int i = 0; i<defaultDesc.size();++i)
                ls[i*2+1]= defaultDesc.get(i);

            try{netherScale = Double.parseDouble(ls[0]);}catch (Exception ignore){}
            ls[0] = String.valueOf(netherScale);

            if (hash != Arrays.hashCode(ls))
                Files.write(confFile.toPath(), Arrays.asList(ls));
            LOGGER.log(Level.INFO,mod+" successfully loaded config file");
        } catch(Exception e) {
            LOGGER.log(Level.ERROR,mod+" failed to load config file, using defaults\n"+e);
        }
    }
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        switch (mixinClassName){
            case mod+".mixin.MixinEntity":
                return netherScale!=8.0 || true;
            default:
                return true;
        }
    }
    @Override public String getRefMapperConfig() { return null; }
    @Override public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) { }
    @Override public List<String> getMixins() { return null; }
    @Override public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
    @Override public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }
}