package net.sacredisle.engine.model;

import com.google.common.collect.Lists;
import net.sacredisle.engine.api.model.Model;
import net.sacredisle.engine.api.model.ModelEngine;
import net.sacredisle.engine.api.model.entity.EntityModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Giovanni on 1/11/2022
 */
public final class Sacred3D implements ModelEngine {

    public static final Logger LOG = LoggerFactory.getLogger(Sacred3D.class);
    private final List<Model<?>> models;
    private final Path modelsPath;
    private boolean enabled;

    public Sacred3D(@NotNull Path modelsPath) {
        this.models = Lists.newArrayList();
        this.modelsPath = modelsPath;
    }

    /**
     * Returns a {@link BlockModel} by name.
     */
    public BlockModel getBlockModel(String name) {
        return (BlockModel) getModel(Model.Type.BLOCK, name);
    }

    /**
     * Returns a {@link ItemModel} by name.
     */
    public ItemModel getItemModel(String name) {
        return (ItemModel) getModel(Model.Type.ITEM, name);
    }

    @Override
    @Nullable
    public Model<?> getModel(Model.Type modelType, String name) {
        if (!isEnabled()) return null;
        for (Model<?> model : models) {
            /* Some models may have the same name across different types */
            /* e.g a Diamond as block and a Diamond as item, so check the type first */
            if (model.getType() != modelType) continue;
            if (model.getName().equalsIgnoreCase(name)) return model;
        }
        return null;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean b) {
        if (this.enabled != b) {
            this.enabled = b;
            if (models.isEmpty()) {
                LOG.info("Loading 3D data..");
                try {
                    readModels();
                } catch (IOException e) {
                    LOG.error("There was an issue whilst checking the Sacred3D data directories:");
                    e.printStackTrace();
                    return;
                }
            }
            for (Model<?> model : models)
                if (model instanceof EntityModel) ((EntityModel) model).setEnabled(b);
        }
    }

    @Override
    public List<Model<?>> getModels() {
        return models;
    }

    // TODO instead of this, move to a serializer inside of the model's class
    private void readModels() throws IOException {
        if (!Files.exists(modelsPath)) Files.createDirectories(modelsPath);
        Path itemsDir = Path.of(modelsPath + "/items/");
        Path blocksDir = Path.of(modelsPath + "/blocks/");

        if (!Files.exists(itemsDir)) Files.createDirectory(itemsDir);
        if (!Files.exists(blocksDir)) Files.createDirectory(blocksDir);

        Files.list(itemsDir).forEach(item3D -> {
            if (Files.isDirectory(item3D)) return;
            if (item3D.toString().toLowerCase().endsWith(".s3d")) {
                try (Stream<String> lines = Files.lines(item3D)) {
                    String name = null;
                    int data = -1;
                    for (Object oLine : lines.toArray()) {
                        String line = (String) oLine;
                        if (line.toLowerCase().startsWith("name:")) name = readName(line, item3D);
                        if (line.toLowerCase().startsWith("data:")) data = readData(line, item3D);
                    }
                    assert name != null;
                    assert data > -1;
                    models.add(new ItemModel(name, data));
                    LOG.info("Registered 3D item: " + name + " with data " + data);
                } catch (IOException ignored) {
                    LOG.error("Failed to load 3D model(item) for " + item3D);
                }
            }
        });
    }

    private String readName(String line, Path item3D) {
        String[] parts = line.split(":");
        if (parts.length < 1) {
            LOG.error("Invalid 3D item model(" + item3D + ") name key could not be found.");
            return null;
        }
        return parts[1].replaceAll(" ", "");
    }

    private int readData(String line, Path item3D) {
        String[] parts = line.split(":");
        if (parts.length < 1) {
            LOG.error("Invalid 3D item model(" + item3D + ") data key could not be found.");
            return -1;
        }
        int data = -1;
        try {
            data = Integer.parseInt(parts[1].replaceAll(" ", ""));
        } catch (NumberFormatException e) {
            LOG.error("Invalid 3D item model(" + item3D + ") data key must be of integer type.");
        }
        return data;
    }
}
