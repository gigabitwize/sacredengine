package net.sacredisle.engine.ui.lib;

/**
 * Created by Giovanni on 1/17/2022
 */
public record UIProperties(String uiName,
                           String title,
                           boolean resizable,
                           boolean borderless) {

    public UIProperties(String uiName,
                        String title,
                        boolean resizable,
                        boolean borderless) {
        this.uiName = uiName;
        this.title = title;
        this.resizable = resizable;
        this.borderless = borderless;
    }
}
