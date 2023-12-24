package club.someoneice.makpiraaqarvik.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Deprecated
public final class Config {
    private Config() {}

    @Target({ElementType.FIELD})
    public @interface Name {
        String name();
    }

    @Target({ElementType.FIELD})
    public @interface Comment {
        String note() default "";
    }

    @Target({ElementType.FIELD})
    public @interface Value {
        String max() default "0";
        String min() default "1";
    }
}
