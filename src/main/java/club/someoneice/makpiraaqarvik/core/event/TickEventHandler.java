package club.someoneice.makpiraaqarvik.core.event;

import club.someoneice.makpiraaqarvik.core.AmarokMakpiraaqarvik;
import club.someoneice.makpiraaqarvik.lib.event.Phase;
import club.someoneice.makpiraaqarvik.lib.event.PlayerLeaveEvent;
import club.someoneice.makpiraaqarvik.lib.event.SecondsTickEvent;
import com.google.common.collect.Maps;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.Map;

@EventBusSubscriber(modid = AmarokMakpiraaqarvik.ID)
public class TickEventHandler {
    static int clientTick, serverTick;
    static final Map<String, Integer> worldTickMap = Maps.newHashMap();
    static final Map<String, Integer> playerTickMap = Maps.newHashMap();

    @SubscribeEvent
    public static void clientTickEvent(ClientTickEvent event) {
        clientTick++;
        if (clientTick >= 20) {
            if (event instanceof ClientTickEvent.Pre) {
                NeoForge.EVENT_BUS.post(new SecondsTickEvent.Client(Phase.PRE));
            } else {
                NeoForge.EVENT_BUS.post(new SecondsTickEvent.Client(Phase.POST));
            }
            clientTick = 0;
        }
    }

    @SubscribeEvent
    public static void serverTickEvent(ServerTickEvent event) {
        serverTick++;
        if (serverTick >= 20) {
            if (event instanceof ServerTickEvent.Pre) {
                NeoForge.EVENT_BUS.post(new SecondsTickEvent.Server(Phase.PRE, event.hasTime(), event.getServer()));
            } else {
                NeoForge.EVENT_BUS.post(new SecondsTickEvent.Server(Phase.POST, event.hasTime(), event.getServer()));
            }
            serverTick = 0;
        }
    }

    @SubscribeEvent
    public static void worldTickEvent(LevelTickEvent event) {
        final String worldName = event.getLevel().dimension().toString();
        final int tick = worldTickMap.getOrDefault(worldName, 0);
        final boolean flag = tick >= 20;
        worldTickMap.put(worldName, flag ? 0 : tick + 1);

        if (!flag) {
            return;
        }

        if (event instanceof LevelTickEvent.Pre) {
            NeoForge.EVENT_BUS.post(new SecondsTickEvent.World(Phase.PRE, event.hasTime(), event.getLevel()));
        } else {
            NeoForge.EVENT_BUS.post(new SecondsTickEvent.World(Phase.POST, event.hasTime(), event.getLevel()));
        }
    }

    @SubscribeEvent
    public static void playerTickEvent(PlayerTickEvent event) {
        final String name = event.getEntity().getScoreboardName();
        final int tick = playerTickMap.getOrDefault(name, 0);
        final boolean flag = tick >= 20;
        playerTickMap.put(name, flag ? 0 : tick + 1);

        if (!flag) {
            return;
        }

        if (event instanceof PlayerTickEvent.Pre) {
            NeoForge.EVENT_BUS.post(new SecondsTickEvent.Player(Phase.PRE, event.getEntity()));
        } else {
            NeoForge.EVENT_BUS.post(new SecondsTickEvent.Player(Phase.POST, event.getEntity()));
        }
    }

    @SubscribeEvent
    public static void onPlayerLeaveEvent(PlayerLeaveEvent event) {
        playerTickMap.remove(event.getEntity().getScoreboardName());
    }
}
