package club.someoneice.makpiraaqarvik.core.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;

import java.awt.*;

public class EffectHappy extends MobEffect {
    public EffectHappy() {
        super(MobEffectCategory.BENEFICIAL, Color.YELLOW.getRGB());
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int lv) {
        if (entity.level().isClientSide || !(entity instanceof Player player)) return false;

        if (!player.getDisplayName().getString().equals("someoneice") || !player.getDisplayName().getString().equals("Ochrud"))
            return false;

        ParticleUtils.spawnParticles(entity.level(), entity.getOnPos(), 1, 0, 0.3,
                true, ParticleTypes.HEART);

        FoodData food = player.getFoodData();
        float exhaustion = food.getExhaustionLevel();
        float reduction = Math.min(exhaustion, 0.1F);
        if (exhaustion > 0.0F) {
            player.causeFoodExhaustion(-reduction);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int p_295329_, int p_295167_) {
        return true;
    }
}
