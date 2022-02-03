package net.sacredisle.engine.ui;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sacredisle.engine.SacredEngine;
import net.sacredisle.engine.ui.lib.UI;
import net.sacredisle.engine.ui.lib.UIAbstract;
import net.sacredisle.engine.ui.lib.UIProperties;

/**
 * Created by Giovanni on 1/30/2022
 */
public class UIMain extends UIAbstract {

    public UIMain(Stage stage,
                  UI parent,
                  UIProperties properties) {
        super(stage, parent, properties);
    }

    @Override
    public void close() {
        SacredEngine.get().exit();
    }

    @Override
    public Class<? extends Controller> getController() {
        return MainController.class;
    }

    public static class MainController implements Controller {

        @Override
        public void onExitClick(MouseEvent mouseEvent) {
        }
    }
}
