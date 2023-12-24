package club.someoneice.makpiraaqarvik.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TileNbt {
    String name();
}
