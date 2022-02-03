package net.sacredisle.engine.lighting.exception;

import net.sacredisle.engine.api.lighting.Volume;

/**
 * Created by Giovanni on 1/11/2022
 */
public class VolumeAlreadyRegisteredException extends RuntimeException {

    public VolumeAlreadyRegisteredException(Volume volume) {
        super(volume.getId() + " has already been registered in SacredLighting");
    }
}
