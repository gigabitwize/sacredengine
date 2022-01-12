package net.sacredisle.engine.api.util;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

/**
 * Created by Giovanni on 1/12/2022
 */
public class Ports {

    /**
     * Checks whether a port is available or not.
     */
    public static boolean isAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port); DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
