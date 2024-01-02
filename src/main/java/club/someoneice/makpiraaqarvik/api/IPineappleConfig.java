package club.someoneice.makpiraaqarvik.api;

import club.someoneice.makpiraaqarvik.config.ConfigBean;

public interface IPineappleConfig {
    void init();
    default IPineappleConfig reload() {
        if (this instanceof ConfigBean bean) bean.readFileAndOverrideConfig();
        this.init();
        return this;
    }
}
