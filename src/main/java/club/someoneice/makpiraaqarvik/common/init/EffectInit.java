package club.someoneice.makpiraaqarvik.common.init;

import club.someoneice.makpiraaqarvik.Main;
import club.someoneice.makpiraaqarvik.effect.EffectHappy;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EffectInit {
    public static DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Main.MODID);

    public static DeferredHolder<MobEffect, MobEffect> HAPPY = EFFECTS.register("happy", EffectHappy::new);
}
