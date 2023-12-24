package club.someoneice.makpiraaqarvik.common.event;

import club.someoneice.makpiraaqarvik.Main;
import club.someoneice.makpiraaqarvik.event.PineappleTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TickEvent;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class TickEventHandler {
    static int clientTick, serverTick, worldTick, playerTick;

    @SubscribeEvent
    public static void clientTickEvent(TickEvent.ClientTickEvent event) {
        clientTick++;
        if (clientTick >= 20) {
            NeoForge.EVENT_BUS.post(new PineappleTickEvent.ClientSecondEvent());
            clientTick = 0;
        }
    }

    @SubscribeEvent
    public static void serverTickEvent(TickEvent.ServerTickEvent event) {
        serverTick++;
        if (serverTick >= 20) {
            NeoForge.EVENT_BUS.post(new PineappleTickEvent.ServerSecondEvent());
            serverTick = 0;
        }
    }

    @SubscribeEvent
    public static void serverTickEvent(TickEvent.LevelTickEvent event) {
        worldTick++;
        if (worldTick >= 20) {
            NeoForge.EVENT_BUS.post(new PineappleTickEvent.WorldSecondEvent(event.level));
            worldTick = 0;
        }
    }

    @SubscribeEvent
    public static void serverTickEvent(TickEvent.PlayerTickEvent event) {
        playerTick++;
        if (playerTick >= 20) {
            NeoForge.EVENT_BUS.post(new PineappleTickEvent.PlayerSecondEvent(event.player));
            playerTick = 0;
        }
    }
}
