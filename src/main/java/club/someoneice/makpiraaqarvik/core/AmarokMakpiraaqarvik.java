package club.someoneice.makpiraaqarvik.core;

import club.someoneice.makpiraaqarvik.core.init.EffectInit;
import club.someoneice.makpiraaqarvik.lib.ItemInfoHelper;
import club.someoneice.makpiraaqarvik.lib.ObjectUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(AmarokMakpiraaqarvik.ID)
public class AmarokMakpiraaqarvik {
    public static final String ID = "amarokmakpiraaqarvik";
    public static final Logger LOGGER = LogManager.getLogger(ID);

    public AmarokMakpiraaqarvik(IEventBus modEventBus, Dist dist, ModContainer container) {
        EffectInit.EFFECTS.register(modEventBus);
        modEventBus.addListener(AmarokMakpiraaqarvik::itemInfoEvent);
    }

    static void itemInfoEvent(ItemTooltipEvent event) {
        var item = event.getItemStack().getItem();
        ObjectUtils.let(ItemInfoHelper.getHandler().get(item), it -> it.forEach(infoBox ->
                infoBox.addInfo(event.getItemStack(), event.getEntity(), event.getToolTip(), event.getFlags())));
    }
}
