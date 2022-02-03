package net.sacredisle.engine;

import net.sacredisle.engine.exception.ServerStartException;
import net.sacredisle.engine.ui.EngineUI;

/**
 * Created by Giovanni on 1/30/2022
 */
public class Bootstrap {

    public static final SacredEngine.Environment BUILD = SacredEngine.Environment.LOCAL;
    private static SacredEngine ENGINE;
    private static EngineUI UI;

    public static void main(String[] args) {
        try {
            ENGINE = new SacredEngine(BUILD);
            if (BUILD == SacredEngine.Environment.LOCAL) {
                UI = new EngineUI();
                UI.initialize();
            }
        } catch (ServerStartException e) {
            e.printStackTrace();
        }
    }
}
