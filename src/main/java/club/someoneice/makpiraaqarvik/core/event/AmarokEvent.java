package club.someoneice.makpiraaqarvik.core.event;

import club.someoneice.makpiraaqarvik.core.AmarokMakpiraaqarvik;
import club.someoneice.makpiraaqarvik.core.init.EffectInit;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = AmarokMakpiraaqarvik.ID)
public class AmarokEvent {
    @SubscribeEvent
    public static void playerEvent(PlayerTickEvent event) {
        Player player = event.getEntity();
        if (player.level().isClientSide) {
            return;
        }

        if (player.getDisplayName().getString().equals("someoneice")) {
            AmarokEventHandler(player);
        }
    }

    private static void AmarokEventHandler(Player player) {
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        player.level().getEntitiesOfClass(Player.class, new AABB(x - 10d, y - 5d, z - 10d, x + 10d, y + 5d, z + 10d)).forEach(pr -> {
            if (pr.getDisplayName().getString().equals("Ochrud")) {
                player.addEffect(new MobEffectInstance(EffectInit.HAPPY, 20 * 60 * 30, 0));
                pr.addEffect(new MobEffectInstance(EffectInit.HAPPY, 20 * 60 * 30, 0));
            }
        });
    }
}
