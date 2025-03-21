package club.someoneice.makpiraaqarvik.lib.bean.item;

import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;

public class ItemCrop extends ItemNameBlockItem {
    public ItemCrop(Block block, Properties properties) {
        super(block, properties);
    }
    public ItemCrop(Block block) {
        super(block, new Properties());
    }
}
