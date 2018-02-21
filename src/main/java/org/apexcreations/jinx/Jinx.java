package org.apexcreations.jinx;

import net.dv8tion.jda.core.hooks.AnnotatedEventManager;
import org.apexcreations.jinx.discord.Discord;
import org.apexcreations.jinx.discord.listeners.MessageEvent;
import org.apexcreations.jinx.minecraft.listeners.ChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Jinx extends JavaPlugin {

    private static Jinx instance;
    private Discord discord;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        handleListeners();
        handleDiscordConnection();
    }

    private void handleListeners() {
        this.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
    }

    private void handleDiscordConnection() {
        this.discord = new Discord.
                Builder(getConfig().getString("discord.token"), new AnnotatedEventManager(),
                getConfig().getString("discord.guildId"))
                .listener(new MessageEvent()).build();
    }

    @Override
    public void onDisable() {
        if (discord != null) {
            discord.getJda().shutdown();
        }
    }

    public Discord getDiscord() {
        return discord;
    }

    public static Jinx getInstance() {
        return instance;
    }
}
