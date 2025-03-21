package club.someoneice.makpiraaqarvik.core.event;

import club.someoneice.makpiraaqarvik.core.AmarokMakpiraaqarvik;
import club.someoneice.makpiraaqarvik.core.init.EffectInit;
import club.someoneice.makpiraaqarvik.lib.event.PlayerJoinEvent;
import club.someoneice.makpiraaqarvik.lib.event.PlayerLeaveEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.function.Consumer;

@EventBusSubscriber(modid = AmarokMakpiraaqarvik.ID)
public class AmarokEvent {
    static boolean flagAmarok;
    static boolean flagOchrud;
    static boolean isRegistry;
    static Consumer<PlayerTickEvent.Pre> amarokevent = AmarokEvent::playerTickEvent;

    @SubscribeEvent
    public static void onPlayerJoinEvent(PlayerJoinEvent event) {
        String name = event.getEntity().getDisplayName().toString();
        flagAmarok = !flagAmarok ? name.equals("someoneice")    : flagAmarok;
        flagOchrud = !flagOchrud ? name.equals("Ochrud")        : flagOchrud;

        if (!isRegistry && flagAmarok && flagOchrud) {
            NeoForge.EVENT_BUS.addListener(amarokevent);
        }
    }

    @SubscribeEvent
    public static void onPlayerLeaveEvent(PlayerLeaveEvent event) {
        String name = event.getEntity().getDisplayName().toString();

        flagAmarok = flagAmarok ? !name.equals("someoneice") : flagAmarok;
        flagOchrud = flagOchrud ? !name.equals("Ochrud") : flagOchrud;

        if (isRegistry && (!flagAmarok || !flagOchrud)) {
            NeoForge.EVENT_BUS.unregister(amarokevent);
        }
    }


    private static void playerTickEvent(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) {
            return;
        }

        if (player.getDisplayName().getString().equals("someoneice")) {
            amarokEventHandler(player);
        }
    }

    private static void amarokEventHandler(Player amarok) {
        Player pr = amarok.level().players().stream()
                .filter(it -> it.getDisplayName().getString().equals("Ochrud"))
                .findFirst()
                .orElse(null);

        if (pr == null) {
            return;
        }

        int dist = amarok.getOnPos().distManhattan(pr.getOnPos());
        if (dist < 15) {
            amarok.addEffect(new MobEffectInstance(EffectInit.HAPPY, 20 * 60 * 30, 0));
            pr.addEffect(new MobEffectInstance(EffectInit.HAPPY, 20 * 60 * 30, 0));
        }
    }
}
