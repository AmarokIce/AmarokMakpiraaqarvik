package club.someoneice.makpiraaqarvik.common.event;

import club.someoneice.makpiraaqarvik.Main;
import club.someoneice.makpiraaqarvik.common.init.EffectInit;
import club.someoneice.makpiraaqarvik.event.PineappleTickEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AmarokEvent {
    @SubscribeEvent
    public static void playerEvent(PineappleTickEvent.PlayerSecondEvent event) {
        Player player = event.getPlayer();
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
                player.addEffect(new MobEffectInstance(EffectInit.HAPPY.get(), 20 * 60 * 30, 0));
                pr.addEffect(new MobEffectInstance(EffectInit.HAPPY.get(), 20 * 60 * 30, 0));
            }
        });

    }
}
