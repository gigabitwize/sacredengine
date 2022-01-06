package net.sacredisle.rpgengine.core.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.Instance;
import net.sacredisle.rpgengine.core.instance.RPGWorldInstance;
import net.sacredisle.rpgengine.core.permission.CommandPermissions;

import java.util.Set;

/**
 * Created by Giovanni on 1/4/2022
 */
public class InstancesCommand extends RPGCommand {

    public InstancesCommand() {
        super("instances", "list-instances");

        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("");
            Set<Instance> instances = MinecraftServer.getInstanceManager().getInstances();
            sender.sendMessage(Component.text("┌ List of available instances:").color(NamedTextColor.GOLD));
            if (instances.isEmpty()) {
                sender.sendMessage(Component.text("None").color(NamedTextColor.RED));
                return;
            }

            StringBuilder stringBuilder = new StringBuilder();
            for (Instance instance : instances) {
                RPGWorldInstance rpgInstance = (RPGWorldInstance) instance;
                sender.sendMessage(Component.text("├ ").append(Component.text(rpgInstance.getLiveName())).color(NamedTextColor.YELLOW));
            }
        });
    }

    @Override
    public String getPermission() {
        return CommandPermissions.EXEC_LIST_INSTANCES.getPermissionName();
    }
}
