package club.someoneice.makpiraaqarvik;

import club.someoneice.makpiraaqarvik.common.init.EffectInit;
import club.someoneice.makpiraaqarvik.core.ItemInfoHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.ID)
public class Main {
    public static final String ID = "amarok_makpiraaqarvik";
    public static final Logger LOGGER = LogManager.getLogger(ID);

    public Main(IEventBus modEventBus, Dist dist, ModContainer container) {
        EffectInit.EFFECTS.register(modEventBus);
        modEventBus.addListener(ItemInfoHelper::itemInfoEvent);
    }
}
