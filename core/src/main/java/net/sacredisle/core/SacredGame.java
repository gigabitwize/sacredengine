package net.sacredisle.core;

import net.sacredisle.engine.Environment;
import net.sacredisle.engine.SacredEngine;
import net.sacredisle.engine.exception.ServerStartException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Giovanni on 1/11/2022
 */
public class SacredGame {

    public static final Logger LOG = LoggerFactory.getLogger(SacredGame.class);
    public static SacredEngine ENGINE;

    public static void main(String[] args) {
        try {
            ENGINE = new SacredEngine(Environment.LOCAL);
        } catch (ServerStartException e) {
            e.printStackTrace();
        }
    }
}
