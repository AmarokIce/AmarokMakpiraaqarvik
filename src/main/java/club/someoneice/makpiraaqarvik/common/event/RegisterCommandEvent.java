package club.someoneice.makpiraaqarvik.common.event;

import club.someoneice.makpiraaqarvik.Main;
import club.someoneice.makpiraaqarvik.common.command.CommandReplaceConfig;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class RegisterCommandEvent {
    @SubscribeEvent
    public static void registerCommand(ServerStartedEvent event) {
        event.getServer().getCommands().getDispatcher().register(CommandReplaceConfig.commandReplaceConfig());
    }
}
