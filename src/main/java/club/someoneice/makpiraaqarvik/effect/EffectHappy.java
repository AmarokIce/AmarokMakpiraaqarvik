package club.someoneice.makpiraaqarvik.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;

import java.awt.*;

public class EffectHappy extends MobEffect {
    public EffectHappy() {
        super(MobEffectCategory.BENEFICIAL, Color.YELLOW.getRGB());
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int lv) {
        if (entity.level().isClientSide || !(entity instanceof Player player)) return;

        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 10, 0));

        if (!player.getDisplayName().getString().equals("someoneice") || !player.getDisplayName().getString().equals("Ochrud")) return;

        player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 20 * 10, 1));
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20 * 10, 1));

        FoodData food = player.getFoodData();
        float exhaustion = food.getExhaustionLevel();
        float reduction = Math.min(exhaustion, 0.1F);
        if (exhaustion > 0.0F) {
            player.causeFoodExhaustion(-reduction);
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_295329_, int p_295167_) {
        return true;
    }
}
