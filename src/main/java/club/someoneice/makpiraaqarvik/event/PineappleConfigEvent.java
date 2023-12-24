package club.someoneice.makpiraaqarvik.event;

import net.neoforged.bus.api.Event;

import javax.annotation.Nullable;

public class PineappleConfigEvent<T> extends Event {
    private final String fileName;
    private final String configKey;
    private String packageName;
    private T value;

    public PineappleConfigEvent(String fileName, String configKey, T value, @Nullable String packageName) {
        this.fileName = fileName;
        this.configKey = configKey;
        this.packageName = packageName;
        this.value = value;
    }

    public String getFileName() {
        return fileName;
    }

    public String getConfigKey() {
        return configKey;
    }

    @Nullable
    public String getPackageName() {
        return packageName;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
