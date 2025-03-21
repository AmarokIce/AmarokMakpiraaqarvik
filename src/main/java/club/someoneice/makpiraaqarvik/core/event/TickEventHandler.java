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
    public static void clientTickEvent(ClientTickEvent.Pre event) {
        if (++clientTick >= 20) {
            NeoForge.EVENT_BUS.post(new SecondsTickEvent.Client(Phase.PRE));
        }
    }

    @SubscribeEvent
    public static void clientTickEvent(ClientTickEvent.Post event) {
        if (clientTick >= 20) {
            NeoForge.EVENT_BUS.post(new SecondsTickEvent.Client(Phase.POST));
            clientTick = 0;
        }
    }

    @SubscribeEvent
    public static void serverTickEvent(ServerTickEvent.Pre event) {
        if (++serverTick >= 20) {
            NeoForge.EVENT_BUS.post(new SecondsTickEvent.Server(Phase.PRE, event.hasTime(), event.getServer()));
        }
    }

    @SubscribeEvent
    public static void serverTickEvent(ServerTickEvent.Post event) {
        if (serverTick >= 20) {
            NeoForge.EVENT_BUS.post(new SecondsTickEvent.Server(Phase.POST, event.hasTime(), event.getServer()));
            serverTick = 0;
        }
    }

    @SubscribeEvent
    public static void worldTickEvent(LevelTickEvent.Pre event) {
        final String worldName = event.getLevel().dimension().toString();
        final int tick = worldTickMap.getOrDefault(worldName, 0) + 1;
        final boolean flag = tick >= 20;

        if (!flag) {
            return;
        }

        NeoForge.EVENT_BUS.post(new SecondsTickEvent.World(Phase.PRE, event.hasTime(), event.getLevel()));
    }

    @SubscribeEvent
    public static void worldTickEvent(LevelTickEvent.Post event) {
        final String worldName = event.getLevel().dimension().toString();
        final int tick = worldTickMap.getOrDefault(worldName, 0) + 1;
        final boolean flag = tick >= 20;
        worldTickMap.put(worldName, flag ? 0 : tick);

        if (!flag) {
            return;
        }

        NeoForge.EVENT_BUS.post(new SecondsTickEvent.World(Phase.POST, event.hasTime(), event.getLevel()));
    }

    @SubscribeEvent
    public static void playerTickEvent(PlayerTickEvent.Pre event) {
        final String name = event.getEntity().getScoreboardName();
        final int tick = playerTickMap.getOrDefault(name, 0) + 1;
        final boolean flag = tick >= 20;

        if (!flag) {
            return;
        }

        NeoForge.EVENT_BUS.post(new SecondsTickEvent.Player(Phase.PRE, event.getEntity()));

    }

    @SubscribeEvent
    public static void playerTickEvent(PlayerTickEvent.Post event) {
        final String name = event.getEntity().getScoreboardName();
        final int tick = playerTickMap.getOrDefault(name, 0) + 1;
        final boolean flag = tick >= 20;
        playerTickMap.put(name, flag ? 0 : tick);

        if (!flag) {
            return;
        }

        NeoForge.EVENT_BUS.post(new SecondsTickEvent.Player(Phase.PRE, event.getEntity()));
    }

    @SubscribeEvent
    public static void onPlayerLeaveEvent(PlayerLeaveEvent event) {
        playerTickMap.remove(event.getEntity().getScoreboardName());
    }
}
