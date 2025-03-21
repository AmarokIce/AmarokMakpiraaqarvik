package club.someoneice.makpiraaqarvik.lib.bean.item;

import net.minecraft.world.item.Item;

public class ItemBase extends Item {
    public ItemBase(Properties properties) {
        super(properties);
    }
    public ItemBase() {
        this(new Properties());
    }
}
