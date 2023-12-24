package club.someoneice.makpiraaqarvik;

import club.someoneice.makpiraaqarvik.common.init.EffectInit;
import club.someoneice.makpiraaqarvik.core.ItemInfoHelper;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.common.NeoForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MODID)
public class Main {
    public static final String MODID = "amarok_makpiraaqarvik";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public Main() {
        var BUS = NeoForge.EVENT_BUS;
        EffectInit.EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BUS.addListener(ItemInfoHelper::itemInfoEvent);
    }
}
