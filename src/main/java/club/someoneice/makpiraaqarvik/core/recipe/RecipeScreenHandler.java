package club.someoneice.makpiraaqarvik.core.recipe;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class RecipeScreenHandler extends AbstractContainerMenu {
    public static final int RESULT_SLOT = 0;
    private static final int CRAFT_SLOT_START = 1;
    private static final int CRAFT_SLOT_END = 10;
    private static final int INV_SLOT_START = 10;
    private static final int INV_SLOT_END = 37;
    private static final int USE_ROW_SLOT_START = 37;
    private static final int USE_ROW_SLOT_END = 46;
    private final CraftingContainer craftSlots;
    private final ResultContainer resultSlots;
    private final Player player;

    public RecipeScreenHandler(int containerId, Inventory playerInventory) {
        super(MenuType.CRAFTING, containerId);
        this.craftSlots = new TransientCraftingContainer(this, 3, 3);
        this.resultSlots = new ResultContainer();
        this.player = playerInventory.player;
        this.addSlot(new ResultSlot(playerInventory.player, this.craftSlots, this.resultSlots, 0, 124, 35));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 3; ++j) {
                this.addSlot(new Slot(this.craftSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }

        for(int k = 0; k < 3; ++k) {
            for(int i1 = 0; i1 < 9; ++i1) {
                this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for(int l = 0; l < 9; ++l) {
            this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 142));
        }

    }

    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) {
            return itemstack;
        }
        ItemStack itemstack1 = slot.getItem();
        itemstack = itemstack1.copy();
        if (index == 0) {
            if (!this.moveItemStackTo(itemstack1, 10, 46, true)) {
                return ItemStack.EMPTY;
            }

            slot.onQuickCraft(itemstack1, itemstack);
        } else if (index >= 10 && index < 46) {
            if (!this.moveItemStackTo(itemstack1, 1, 10, false)) {
                if (index < 37) {
                    if (!this.moveItemStackTo(itemstack1, 37, 46, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemstack1, 10, 37, false)) {
                    return ItemStack.EMPTY;
                }
            }
        } else if (!this.moveItemStackTo(itemstack1, 10, 46, false)) {
            return ItemStack.EMPTY;
        }

        if (itemstack1.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        if (itemstack1.getCount() == itemstack.getCount()) {
            return ItemStack.EMPTY;
        }

        slot.onTake(player, itemstack1);
        if (index == 0) {
            player.drop(itemstack1, false);
        }

        return itemstack;
    }


    @Override
    public boolean stillValid(Player player) {
        return player.isAlive();
    }
}
