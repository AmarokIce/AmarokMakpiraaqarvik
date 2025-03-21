package club.someoneice.makpiraaqarvik.lib.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class PlayerLeaveEvent extends PlayerEvent {
    public PlayerLeaveEvent(Player player) {
        super(player);
    }
}
