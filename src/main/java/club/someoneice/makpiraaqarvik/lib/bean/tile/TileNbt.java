package club.someoneice.makpiraaqarvik.lib.bean.tile;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TileNbt {
    String name();
}
