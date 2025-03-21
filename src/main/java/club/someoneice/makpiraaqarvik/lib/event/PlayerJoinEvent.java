package club.someoneice.makpiraaqarvik.lib.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

/**
 * When the player joins the server, it will fire.
 * It's server only.
 */
public class PlayerJoinEvent extends PlayerEvent implements ICancellableEvent {
    public PlayerJoinEvent(Player entity) {
        super(entity);
    }
}
