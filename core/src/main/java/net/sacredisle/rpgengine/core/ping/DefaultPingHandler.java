package net.sacredisle.rpgengine.core.ping;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.util.RGBLike;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.server.ServerListPingEvent;
import net.minestom.server.ping.ResponseData;
import net.sacredisle.rpgengine.api.Engine;
import net.sacredisle.rpgengine.api.MOTD;
import net.sacredisle.rpgengine.api.ping.Ping;
import net.sacredisle.rpgengine.api.ping.PingHandler;
import net.sacredisle.rpgengine.core.RPGEngine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * Created by Giovanni on 1/5/2022
 */
public class DefaultPingHandler implements PingHandler, EventListener<ServerListPingEvent> {

    public MOTD motd = new MOTD() {
        @Override
        public TextComponent getFirstLine() {
            return Component.text("Sacred RPGEngine").color(TextColor.color(new RGBLike() {
                @Override
                public @Range(from = 0L, to = 255L) int red() {
                    return 217;
                }

                @Override
                public @Range(from = 0L, to = 255L) int green() {
                    return 24;
                }

                @Override
                public @Range(from = 0L, to = 255L) int blue() {
                    return 88;
                }
                /* Engine version appended at end of first line */
            })).append(Component.text("                                   " + Engine.getBuildVersion()).color(NamedTextColor.GRAY)
                    .append(Component.text(" ⚙").color(NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD)));
        }

        @Override
        public @NotNull TextComponent getSecondLine() {
            return Component.text("");
        }
    };

    @Override
    public @NotNull Result run(@NotNull ServerListPingEvent event) {
        ResponseData responseData = event.getResponseData();
        responseData.setOnline(MinecraftServer.getConnectionManager().getOnlinePlayers().size());
        responseData.setMaxPlayer(RPGEngine.MAX_PLAYERS);

        switch (event.getPingType()) {
            case LEGACY_VERSIONED, LEGACY_UNVERSIONED -> versionNotSupported(event);
            case MODERN_FULL_RGB, MODERN_NAMED_COLORS -> {
                versionSupported(event);
            }
        }
        return Result.SUCCESS;
    }

    @Override
    public @NotNull Class<ServerListPingEvent> eventType() {
        return ServerListPingEvent.class;
    }

    @Override
    public Ping.Response handle(Ping ping) {
        return null; //TODO
    }

    private void versionNotSupported(ServerListPingEvent event) {
        event.getResponseData().setVersion("1.18");
        event.getResponseData().setDescription(
                Component.text("Your Minecraft version is not supported!").color(NamedTextColor.RED)
                        .append(Component.text("\n"))
                        .append(Component.text("Please update your Minecraft version to 1.18 to play").color(NamedTextColor.RED)));
    }

    private void versionSupported(ServerListPingEvent event) {
        event.getResponseData().setVersion("1.18");
        event.getResponseData().setDescription(motd.getFirstLine().append(Component.text("\n")).append(motd.getSecondLine()));
    }
}
