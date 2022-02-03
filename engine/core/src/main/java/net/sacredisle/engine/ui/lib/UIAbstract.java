package net.sacredisle.engine.ui.lib;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sacredisle.engine.Bootstrap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * Created by Giovanni on 1/30/2022
 */
public abstract class UIAbstract implements UI {

    private final Stage stage;
    private final UI parent;
    private final UIProperties properties;
    private final FXMLLoader loader;

    private Scene scene;

    public UIAbstract(@NotNull Stage stage,
                      @Nullable UI parent,
                      @NotNull UIProperties properties) {
        this.stage = stage;
        this.parent = Objects.requireNonNullElse(parent, this);
        this.properties = properties;
        this.loader = new FXMLLoader(Bootstrap.class.getResource("/" + properties.uiName() + ".fxml"));
    }

    @Override
    public void open() throws IOException {
        if (scene == null)
            scene = new Scene(loader.load());
        getStage().setScene(scene);
        getStage().setTitle(properties.title());
        getStage().setResizable(properties.resizable());
        if (properties.borderless())
            getStage().initStyle(StageStyle.UNDECORATED);
        getStage().show();
    }

    @Override
    public void close() throws IOException {
        parent.open();
    }

    @Override
    public UI getParent() {
        return parent;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public UIProperties getProperties() {
        return properties;
    }

    @Override
    public FXMLLoader getLoader() {
        return loader;
    }
}
