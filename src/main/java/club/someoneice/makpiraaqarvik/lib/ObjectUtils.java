package club.someoneice.makpiraaqarvik.lib;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ObjectUtils {
    public static <T> T apply(final T obj, final Function<T, T> run) {
        return run.apply(Objects.requireNonNull(obj));
    }

    public static void runIf(final boolean b, @Nonnull final Runnable runTrue, @Nullable final Runnable runFalse) {
        if (b) runTrue.run();
        else let(runFalse, Runnable::run);
    }

    public static void runIf(final boolean b, @Nonnull final Runnable runTrue) {
        runIf(b, runTrue, null);
    }


    public static <T> void let(final T obj, final Consumer<T> run) {
        if (Objects.nonNull(obj)) run.accept(obj);
    }

    public static <T, F> F make(final T obj, Function<T, F> run) {
        return run.apply(Objects.requireNonNull(obj));
    }

    public static <T> T makeIf(final boolean b, @Nonnull final Supplier<T> runTrue, @Nonnull final Supplier<T> runFalse) {
        return b ? runTrue.get() : runFalse.get();
    }

    public static <T> T run(final T obj, final Consumer<T> run) {
        run.accept(obj);
        return obj;
    }

    public static <T> boolean test(T obj, Predicate<T> run) {
        return run.test(Objects.requireNonNull(obj));
    }

    public static <T> void copyByObject(T objOld, T objNew) throws RuntimeException {
        try {
            for (Field field : objOld.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                field.set(objNew, field.get(objOld));
            }
        } catch (Exception e) {
            throw new RuntimeException("Cannot copy class " + objOld.getClass().getSimpleName());
        }
    }
}
