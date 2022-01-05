package net.sacredisle.rpgengine.api.ping;

import net.sacredisle.rpgengine.api.MOTD;

/**
 * Created by Giovanni on 1/5/2022
 */
public interface Ping {

    /**
     * Returns the request code of this ping. The
     * request code is used to determine what to return
     * in the {@link Response}.
     */
    int requestCode();

    public interface Response {

        /**
         * Returns a server {@link MOTD}.
         */
        MOTD getMOTD();

        /**
         * Returns the {@link Result} from the handling process of a Ping.
         */
        Result getResult();

        /**
         * The request code that resulted in this response.
         */
        int requestCode();
    }

    public enum Result {

        SUCCESS,
        FAILED;
    }
}
