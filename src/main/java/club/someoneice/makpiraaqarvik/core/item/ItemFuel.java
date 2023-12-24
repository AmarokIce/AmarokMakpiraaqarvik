package club.someoneice.makpiraaqarvik.core.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class ItemFuel extends ItemBase {
    private final int burnTime;
    public ItemFuel(Properties properties, int burnTime) {
        super(properties);
        this.burnTime = burnTime;
    }

    public ItemFuel(int burnTime) {
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(ItemStack item, RecipeType<?> recipe) {
        return burnTime;
    }
}
