package net.sacredisle.engine.ui.lib;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Giovanni on 1/30/2022
 */
public interface UI {

    /**
     * Closes this UI.
     */
    void close() throws IOException;

    /**
     * Opens this UI.
     */
    void open() throws IOException;

    /**
     * Returns the FX {@link Stage}.
     */
    Stage getStage();

    /**
     * Returns the parent UI.
     * <p>
     * e.g the Main Menu.
     */
    UI getParent();

    /**
     * Returns the {@link UIProperties}.
     */
    UIProperties getProperties();

    /**
     * Returns the {@link FXMLLoader}.
     */
    FXMLLoader getLoader();


    /**
     * Returns the {@link Controller} class.
     */
    Class<? extends Controller> getController();

    /**
     * A bridge/interface between the engine and JavaFX.
     */
    interface Controller {

        void onExitClick(MouseEvent mouseEvent);
    }
}
