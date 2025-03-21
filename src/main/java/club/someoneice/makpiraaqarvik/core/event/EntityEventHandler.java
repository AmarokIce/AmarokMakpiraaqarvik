package club.someoneice.makpiraaqarvik.core.event;

import club.someoneice.makpiraaqarvik.lib.event.PlayerJoinEvent;
import club.someoneice.makpiraaqarvik.lib.event.PlayerLeaveEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.EntityLeaveLevelEvent;

@EventBusSubscriber
public class EntityEventHandler {
    @SubscribeEvent
    public static void entityJoinEvent(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide) {
            return;
        }

        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        final ServerLevel world = (ServerLevel) event.getLevel();
        if (!world.players().contains(player)) {
            final boolean flag = NeoForge.EVENT_BUS.post(new PlayerJoinEvent(player)).isCanceled();
            event.setCanceled(flag);
        }
    }

    @SubscribeEvent
    public static void entityLeaveEvent(EntityLeaveLevelEvent event) {
        if (event.getLevel().isClientSide) {
            return;
        }

        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        final ServerLevel world = (ServerLevel) event.getLevel();
        if (!world.players().contains(player)) {
            NeoForge.EVENT_BUS.post(new PlayerLeaveEvent(player));
        }
    }
}
