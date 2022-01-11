package net.sacredisle.engine.api.model;

/**
 * Created by Giovanni on 1/11/2022
 * <p>
 * A barebones 3D model. Can be an item or a block.
 */
public interface Model<A> {

    /**
     * Method used to apply this model to a certain object. For example an ItemStack
     * to apply the model to an item, or a Position to apply the model to a block at
     * said position.
     */
    A apply(A a);

    /**
     * Returns this model's name.
     */
    String getName();

    /**
     * Returns the {@link Type} of model this is.
     */
    Type getType();

    enum Type {
        ITEM,
        BLOCK,
        ENTITY;
    }
}
