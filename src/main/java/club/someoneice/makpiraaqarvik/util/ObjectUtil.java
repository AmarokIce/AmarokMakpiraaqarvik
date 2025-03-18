package club.someoneice.makpiraaqarvik.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ObjectUtil {
    public static <T> T run(final T obj, final Function<T, T> run) {
        return run.apply(Objects.requireNonNull(obj));
    }

    public static void runIf(final Boolean b, @Nonnull final Runnable runTure, @Nullable final Runnable runFalse) {
        if (b) runTure.run();
        else let(runFalse, Runnable::run);
    }

    public static <T> void let(T obj, Consumer<T> run) {
        if (Objects.nonNull(obj)) run.accept(obj);
    }

    public static <T, F> F apply(T obj, Function<T, F> run) {
        return run.apply(Objects.requireNonNull(obj));
    }

    public static <T> boolean test(T obj, Predicate<T> run) {
        return run.test(Objects.requireNonNull(obj));
    }
}
