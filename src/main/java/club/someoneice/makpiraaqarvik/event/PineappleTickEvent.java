package club.someoneice.makpiraaqarvik.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.Event;

public class PineappleTickEvent extends Event {
    public static class ClientSecondEvent extends PineappleTickEvent { }
    public static class ServerSecondEvent extends PineappleTickEvent { }
    public static class WorldSecondEvent extends PineappleTickEvent {
        private final Level world;
        public WorldSecondEvent(Level world) {
            this.world = world;
        }

        public Level getWorld() {
            return this.world;
        }
    }

    public static class PlayerSecondEvent extends PineappleTickEvent {
        private final Player player;
        public PlayerSecondEvent(Player player) {
            this.player = player;
        }

        public Player getPlayer() {
            return this.player;
        }
    }
}
