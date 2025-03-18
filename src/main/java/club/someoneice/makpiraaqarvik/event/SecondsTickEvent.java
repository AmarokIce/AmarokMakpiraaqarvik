package club.someoneice.makpiraaqarvik.event;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.Event;


public class SecondsTickEvent extends Event {
    public final Phase phase;
    public SecondsTickEvent(Phase phase) {
        this.phase = phase;
    }

    public static class Client extends SecondsTickEvent {
        public Client(Phase phase) {
            super(phase);
        }
    }
    public static class Server extends SecondsTickEvent {
        public final boolean hasTime;
        public final MinecraftServer server;

        public Server(Phase phase, boolean hasTime, MinecraftServer server) {
            super(phase);
            this.hasTime = hasTime;
            this.server = server;
        }
    }

    public static class Player extends SecondsTickEvent {
        public final net.minecraft.world.entity.player.Player player;
        public Player(Phase phase, net.minecraft.world.entity.player.Player player) {
            super(phase);
            this.player = player;
        }
    }

    public static class World extends SecondsTickEvent {
        public final boolean hasTime;
        public final Level world;

        public World(Phase phase, boolean hasTime, Level world) {
            super(phase);
            this.hasTime = hasTime;
            this.world = world;
        }
    }
}
