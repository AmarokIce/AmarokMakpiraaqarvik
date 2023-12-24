package club.someoneice.makpiraaqarvik.util;

// import lombok.SneakyThrows;

import net.minecraft.world.item.Item;

import java.lang.reflect.Field;

public class Util {
    public static Item.Properties properties = new Item.Properties();

    // @SneakyThrows
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
