package net.sacredisle.rpgengine.core.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.builder.arguments.ArgumentString;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.condition.Conditions;
import net.minestom.server.instance.Instance;
import net.sacredisle.rpgengine.core.instance.RPGWorldInstance;
import net.sacredisle.rpgengine.core.permission.CommandPermissions;
import net.sacredisle.rpgengine.core.player.RPGPlayer;

/**
 * Created by Giovanni on 1/4/2022
 */
public class SwitchInstanceCommand extends RPGCommand {

    public SwitchInstanceCommand() {
        super("switch", "si");

        setCondition(Conditions::playerOnly);

        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("");
            sender.sendMessage(Component.text("Incorrect syntax, use: /switch <name>").color(NamedTextColor.RED));
        });

        ArgumentString nameArgument = ArgumentType.String("name");
        addSyntax((sender, context) -> {
            String name = context.get(nameArgument);
            RPGPlayer rpgPlayer = (RPGPlayer) sender;

            for (Instance instance : MinecraftServer.getInstanceManager().getInstances()) {
                RPGWorldInstance rpgInstance = (RPGWorldInstance) instance;
                if (rpgInstance.getLiveName().equalsIgnoreCase(name)) {
                    rpgPlayer.setInstance(rpgInstance, rpgInstance.getSpawn());
                    sender.sendMessage("");
                    rpgPlayer.sendMessage(Component.text("You have been teleported to ").color(NamedTextColor.GREEN)
                            .append(rpgInstance.getLiveDisplayName()).color(NamedTextColor.DARK_AQUA));
                    break;
                }
            }
        }, nameArgument);
    }

    @Override
    public String getPermission() {
        return CommandPermissions.EXEC_SWITCH_INSTANCE.getPermissionName();
    }
}
