package club.someoneice.makpiraaqarvik.common.command;

import club.someoneice.makpiraaqarvik.util.ConfigUtil;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class CommandReplaceConfig {
    public static LiteralArgumentBuilder<CommandSourceStack> commandReplaceConfig() {
        return Commands.literal("pineappleConfig").then(Commands.argument("modid", StringArgumentType.string()))
                .executes(it -> {
                    var configName = StringArgumentType.getString(it, "modid");
                    if (ConfigUtil.INITIALIZE.configs.containsKey(configName))
                        ConfigUtil.INITIALIZE.configs.get(configName).reload();
                    return 0;
                });
    }
}
