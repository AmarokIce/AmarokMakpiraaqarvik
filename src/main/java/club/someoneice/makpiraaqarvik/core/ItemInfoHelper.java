package club.someoneice.makpiraaqarvik.core;

import club.someoneice.makpiraaqarvik.Main;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ItemInfoHelper {
    public static final Map<Item, Set<InfoBox>> infoBox = Maps.newHashMap();

    public interface InfoBox {
        void addInfo(ItemStack item, Player player, List<Component> list, TooltipFlag flag);
    }

    public static void addInfoToItem(Item item, InfoBox info) {
        if (infoBox.containsKey(item)) infoBox.get(item).add(info);
        else infoBox.put(item, Sets.newHashSet(info));
    }

    public static void itemInfoEvent(ItemTooltipEvent event) {
        var item = event.getItemStack().getItem();
        if (infoBox.containsKey(item)) {
            infoBox.get(item).forEach(it -> it.addInfo(event.getItemStack(), event.getEntity(), event.getToolTip(), event.getFlags()));
        }
    }
}
