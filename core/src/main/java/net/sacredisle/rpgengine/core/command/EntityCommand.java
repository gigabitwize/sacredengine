package net.sacredisle.rpgengine.core.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.command.builder.condition.Conditions;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.ai.EntityAIGroupBuilder;
import net.minestom.server.entity.ai.goal.RandomLookAroundGoal;
import net.minestom.server.entity.ai.goal.RandomStrollGoal;
import net.minestom.server.entity.fakeplayer.FakePlayer;
import net.minestom.server.entity.fakeplayer.FakePlayerOption;
import net.minestom.server.tag.Tag;
import net.sacredisle.rpgengine.core.entity.RPGCreature;
import net.sacredisle.rpgengine.core.entity.human.RPGHuman;
import net.sacredisle.rpgengine.core.entity.human.RPGHumanCreature;
import net.sacredisle.rpgengine.core.instance.RPGWorldInstance;
import net.sacredisle.rpgengine.core.player.RPGPlayer;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by Giovanni on 1/5/2022
 */
public class EntityCommand extends Command {

    public EntityCommand() {
        super("entity", "entities");
        addSubcommand(new SubCreateEntity());
        addSubcommand(new SubCreateHuman());

        setDefaultExecutor((sender, context) -> {
            sender.sendMessage("");
            sender.sendMessage(Component.text("Incorrect syntax, use: /entity <create/human>").color(NamedTextColor.RED));
        });
    }

    static class SubCreateEntity extends Command {

        public SubCreateEntity() {
            super("create");

            setCondition(Conditions::playerOnly);
            setDefaultExecutor((sender, context) -> {
                sender.sendMessage("");
                sender.sendMessage(Component.text("Incorrect syntax, use: /entity create <entityType> [level] [name]").color(NamedTextColor.RED));
            });

            addSyntax(((sender, context) -> {
                RPGPlayer player = (RPGPlayer) sender;

                String name = context.get("name");

                EntityType entityType = context.get("entityType");
                RPGCreature rpgCreature = new RPGCreature(entityType, context.get("level"), name, Component.text(name));
                rpgCreature.addAIGroup(new EntityAIGroupBuilder()
                        .addGoalSelector(new RandomStrollGoal(rpgCreature, 10))
                        .addGoalSelector(new RandomLookAroundGoal(rpgCreature, 20))
                        .build());
                rpgCreature.setInstance(Objects.requireNonNull(player.getInstance()), player.getPosition());

            }), ArgumentType.EntityType("entityType"), ArgumentType.Integer("level").setDefaultValue(1), ArgumentType.String("name").setDefaultValue("RPGEntity"));
        }
    }

    static class SubCreateHuman extends Command {
        public SubCreateHuman() {
            super("human");

            setCondition(Conditions::playerOnly);
            setDefaultExecutor((sender, context) -> {
                sender.sendMessage("");
                sender.sendMessage(Component.text("Incorrect syntax, use: /entity human <name> [level]").color(NamedTextColor.RED));
            });

            addSyntax(((sender, context) -> {
                RPGPlayer player = (RPGPlayer) sender;

                String name = context.get("name");
                int level = context.get("level");

                RPGHuman human = null;
                if (level < 0)
                    human = new RPGHuman((RPGWorldInstance) player.getInstance(), player.getPosition(), name, Component.text(name));
                else {
                    human = new RPGHumanCreature((RPGWorldInstance) player.getInstance(), player.getPosition(), name, Component.text(name));
                    ((RPGHumanCreature) human).setRPGLevel(level);
                }
            }), ArgumentType.String("name"), ArgumentType.Integer("level").setDefaultValue(-1));
        }
    }
}
