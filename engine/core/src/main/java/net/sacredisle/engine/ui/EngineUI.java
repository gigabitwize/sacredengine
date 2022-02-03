package net.sacredisle.engine.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import net.sacredisle.engine.SacredEngine;
import net.sacredisle.engine.ui.lib.UI;
import net.sacredisle.engine.ui.lib.UIProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Giovanni on 1/30/2022
 * <p>
 * Provides UI functionality.
 */
public final class EngineUI extends Application {

    public static final Logger LOG = LoggerFactory.getLogger(EngineUI.class);
    /* The currently opened UI */
    private UI current;

    /**
     * Starts the UI engine.
     */
    public void initialize() {
        LOG.info("Starting interface..");
        if (SacredEngine.get() == null) {
            LOG.warn("Can't start the interface, the engine is not running.");
            return;
        }
        if (SacredEngine.get().getSacredServer().getEnvironment() != SacredEngine.Environment.LOCAL) {
            LOG.warn("Can't start the interface, the engine is not in a local environment.");
            return;
        }
        launch();
        LOG.info("Interface launched.");
    }

    @Override
    public void start(Stage stage) throws Exception {
        current = new UIMain(stage, null, new UIProperties(
                "menu_main",
                "Sacred Engine | Login",
                false,
                true));
        current.open();
    }

    public UI getCurrentUI() {
        return current;
    }
}
