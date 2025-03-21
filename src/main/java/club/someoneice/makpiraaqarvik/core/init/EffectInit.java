package club.someoneice.makpiraaqarvik.core.init;

import club.someoneice.makpiraaqarvik.core.AmarokMakpiraaqarvik;
import club.someoneice.makpiraaqarvik.core.effect.EffectHappy;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EffectInit {
    public static DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, AmarokMakpiraaqarvik.ID);

    public static DeferredHolder<MobEffect, MobEffect> HAPPY = EFFECTS.register("happy", EffectHappy::new);
}
