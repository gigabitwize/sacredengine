package net.sacredisle.engine.model;

import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;
import net.sacredisle.engine.api.model.Model;
import org.jetbrains.annotations.NotNull;

/**
 * Created by Giovanni on 1/11/2022
 */
public record ItemModel(String name, int data) implements Model<ItemStack> {

    /**
     * @param data Data value corresponding to the model in a resource pack.
     */
    public ItemModel(@NotNull String name, int data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public ItemStack apply(ItemStack itemStack) {
        return itemStack.withTag(Tag.Integer("CustomModelData"), data);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Type getType() {
        return Type.ITEM;
    }
}
