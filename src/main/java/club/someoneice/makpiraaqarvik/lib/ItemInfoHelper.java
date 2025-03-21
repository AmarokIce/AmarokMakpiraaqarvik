package club.someoneice.makpiraaqarvik.lib;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ItemInfoHelper {
    static final Map<Item, Set<InfoBox>> INFO_HOLDER = Maps.newHashMap();

    public interface InfoBox {
        void addInfo(ItemStack item, Player player, List<Component> list, TooltipFlag flag);
    }

    public static void addInfoToItem(Item item, InfoBox info) {
        Set<InfoBox> infos = INFO_HOLDER.getOrDefault(item, Sets.newLinkedHashSet());
        infos.add(info);
        INFO_HOLDER.put(item, infos);
    }

    public static Map<Item, Set<InfoBox>> getHandler() {
        return INFO_HOLDER;
    }
}
