package club.someoneice.makpiraaqarvik.core.item;

import club.someoneice.makpiraaqarvik.util.Util;
import net.minecraft.world.item.Item;

public class ItemBase extends Item {

    public ItemBase(Properties properties) {
        super(properties);
    }

    public ItemBase() {
        this(Util.properties);
    }
}
