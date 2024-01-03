package club.someoneice.makpiraaqarvik.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ObjectUtil {
    public static <T> T run(T t, ObjectRunWithReturn<T> run) {
        return run.run(t);
    }

    public static void runIf(Boolean b, @Nonnull final ObjectRun runTure, @Nullable final ObjectRun runFalse) {
        if (b) runTure.run();
        else let(runFalse, ObjectRun::run);
    }

    public static <T> void let(T obj, ObjectRunWithType<T> run) {
        if (obj != null) run.run(obj);
    }

    public interface ObjectRun {
        void run();
    }

    public interface ObjectRunWithType<T> {
        void run(T t);
    }

    public interface ObjectRunWithReturn<T> {
        T run(T t);
    }
}
