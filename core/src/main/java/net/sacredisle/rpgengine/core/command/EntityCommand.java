package net.sacredisle.rpgengine.core.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.condition.Conditions;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.ai.EntityAIGroup;
import net.minestom.server.entity.ai.EntityAIGroupBuilder;
import net.minestom.server.entity.ai.goal.RandomLookAroundGoal;
import net.minestom.server.entity.ai.goal.RandomStrollGoal;
import net.sacredisle.rpgengine.core.entity.RPGCreature;
import net.sacredisle.rpgengine.core.player.RPGPlayer;

import java.util.Objects;

/**
 * Created by Giovanni on 1/5/2022
 */
public class EntityCommand extends Command {

    public EntityCommand() {
        super("entity", "entities");
        addSubcommand(new SubCreateEntity());
    }

    static class SubCreateEntity extends Command {

        public SubCreateEntity() {
            super("create");

            setCondition(Conditions::playerOnly);
            setDefaultExecutor((sender, context) -> {
                sender.sendMessage("");
                sender.sendMessage(Component.text("Incorrect syntax, use: /entity <create> <entityType> [level] [name]").color(NamedTextColor.RED));
            });

            addSyntax(((sender, context) -> {
                RPGPlayer player = (RPGPlayer) sender;

                EntityType entityType = context.get("entityType");
                RPGCreature rpgCreature = new RPGCreature(entityType, context.get("level"), context.get("name"), context.get("name"));
                rpgCreature.addAIGroup(new EntityAIGroupBuilder()
                        .addGoalSelector(new RandomStrollGoal(rpgCreature, 10))
                        .addGoalSelector(new RandomLookAroundGoal(rpgCreature, 20))
                        .build());
                rpgCreature.setInstance(Objects.requireNonNull(player.getInstance()), player.getPosition());
                rpgCreature.spawn();

            }), ArgumentType.EntityType("entityType"), ArgumentType.Integer("level").setDefaultValue(1), ArgumentType.String("name").setDefaultValue("RPGEntity"));
        }
    }
}
