package club.someoneice.makpiraaqarvik.lib;

import club.someoneice.json.JSON;
import club.someoneice.json.node.JsonNode;
import club.someoneice.json.node.MapNode;
import club.someoneice.json.processor.Json5Builder;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import net.neoforged.fml.loading.FMLPaths;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@SuppressWarnings("unused")
public final class Config {
    private final File configFile;

    private final Map<Command, String> commandList = Maps.newLinkedHashMap();
    private final Set<String> valueSet = Sets.newHashSet();

    private final MapNode configNode;

    public Config(String configName) throws IOException {
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

    @SuppressWarnings("unchecked")
    public <T> T getOrDefault(String string, T defaultValue) {
        if (!valueSet.contains(string)) {
            commandList.put(Command.SET, string);
            valueSet.add(string);
        }

        return (T) ObjectUtils.makeIf(this.configNode.has(string),
                () -> this.configNode.get(string).getObj(),
                () -> this.configNode.put(string, new JsonNode<>(defaultValue)).getObj()
        );
    }

    @SuppressWarnings("unchecked")
    public <T> T safeGetOrDefault(String string, T defaultValue, JsonNode.NodeType type) {
        if (!valueSet.contains(string)) {
            commandList.put(Command.SET, string);
            valueSet.add(string);
        }

        if (!this.configNode.has(string)) {
            this.configNode.put(string, new JsonNode<>(defaultValue));
            return defaultValue;
        }

        JsonNode<?> node = this.configNode.get(string).asTypeOrNull(type);
        if (Objects.isNull(node)) {
            this.configNode.put(string, new JsonNode<>(defaultValue));
            return defaultValue;
        }

        return (T) node.getObj();
    }

    public <T> void set(String string, T value) {
        if (!valueSet.contains(string)) {
            commandList.put(Command.SET, string);
            valueSet.add(string);
        }

        this.configNode.put(string, new JsonNode<>(value));
    }

    public void reload() {
        this.configNode.clear();
        this.configNode.addAll(JSON.json5.parse(this.configFile).asMapNodeOrEmpty());
    }

    public void saveToFile() {
        Json5Builder builder = new Json5Builder();
        Json5Builder.ObjectBean objectBean = builder.getObjectBean();

        this.commandList.forEach((key, value) -> {
            switch(key) {
                case SET -> objectBean.put(value, this.configNode.get(value));
                case COMMIT -> objectBean.addNote(value);
                case ENTER -> objectBean.enterLine();
            }
        });

        builder.put(objectBean);
    }

    enum Command {
        SET,
        COMMIT,
        ENTER
    }
}
