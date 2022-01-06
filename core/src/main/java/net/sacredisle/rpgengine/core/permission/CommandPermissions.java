package net.sacredisle.rpgengine.core.permission;

import net.minestom.server.permission.Permission;

/**
 * Created by Giovanni on 1/6/2022
 * <p>
 * Global constants class for permissions used in commands.
 */
public final class CommandPermissions {

    public static final Permission ALL = new Permission("permission:all");

    /**
     * {@link net.sacredisle.rpgengine.core.command.EntityCommand}.
     */
    public static final Permission EXEC_ENTITY_COMMAND = new Permission("permission:engine.execute.entitydev");

    /**
     * {@link net.sacredisle.rpgengine.core.command.InstancesCommand}.
     */
    public static final Permission EXEC_LIST_INSTANCES = new Permission("permission:engine.execute.listinstancesdev");

    /**
     * {@link net.sacredisle.rpgengine.core.command.GenerateInstanceCommand}.
     */
    public static final Permission EXEC_MANAGE_INSTANCES = new Permission("permission:engine.execute.manageinstancesdev");

    /**
     * {@link net.sacredisle.rpgengine.core.command.SwitchInstanceCommand}.
     */
    public static final Permission EXEC_SWITCH_INSTANCE = new Permission("permission:engine.execute.switchinstancedev");
}
