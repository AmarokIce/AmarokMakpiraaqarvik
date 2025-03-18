package club.someoneice.makpiraaqarvik.config;

import club.someoneice.json.JSON;
import club.someoneice.json.node.MapNode;
import club.someoneice.json.processor.Json5Builder;
import com.google.common.io.Files;
import net.neoforged.fml.loading.FMLPaths;

import java.io.File;
import java.io.IOException;

// TODO
@Deprecated(since = "In rewrite, don't use this!", forRemoval = false)
public final class ConfigBean {
    private final File configFile;

    private final Json5Builder.ObjectBean bean;
    private final MapNode configNode;

    public ConfigBean(String configName) throws IOException {
        this.bean = new Json5Builder.ObjectBean();

        if (!configName.endsWith(".json5")) {
            configName += ".json5";
        }

        final String path = configName.contains(File.pathSeparator)
                ? configName.substring(0, configName.lastIndexOf(File.pathSeparator))
                : "";
        configName = !path.isEmpty() ? configName.replace(path, "") : configName;

        final File configDir = FMLPaths.CONFIGDIR.get().resolve(path).toFile();
        this.configFile = new File(configDir, configName);

        if (!configDir.exists() || !configDir.isDirectory()) {
            if (!configDir.mkdirs()) {
                throw new IOException("Can't create the config dir %s yet!".formatted(configDir.getName()));
            }
        }

        if (!this.configFile.exists() || !this.configFile.isFile()) {
            if (!this.configFile.createNewFile()) {
                throw new IOException("Can't create the config file %s yet!".formatted(configFile.getName()));
            }
            Files.write("{}".getBytes(), this.configFile);
            this.configNode = new MapNode();
            return;
        }

        this.configNode = JSON.json5.parse(this.configFile).asMapNodeOrEmpty();
    }

}
