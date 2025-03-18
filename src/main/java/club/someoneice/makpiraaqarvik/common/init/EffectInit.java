package club.someoneice.makpiraaqarvik.common.init;

import club.someoneice.makpiraaqarvik.Main;
import club.someoneice.makpiraaqarvik.effect.EffectHappy;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EffectInit {
    public static DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Main.ID);

    public static DeferredHolder<MobEffect, MobEffect> HAPPY = EFFECTS.register("happy", EffectHappy::new);
}
