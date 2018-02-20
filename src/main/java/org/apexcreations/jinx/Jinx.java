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
    this.saveDefaultConfig();
    this.handleListeners();
    this.handleDiscordConnection();
  }

  private void handleListeners() {
    this.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
  }

  private void handleDiscordConnection() {
    this.discord = new Discord.
        Builder(this.getConfig().getString("discord.token"), new AnnotatedEventManager(),
        this.getConfig().getString("discord.guildId"))
        .listener(new MessageEvent()).build();
  }

  @Override
  public void onDisable() {
   if (this.discord != null) {
     this.discord.getJda().shutdown();
   }
  }

  public Discord getDiscord() {
    return this.discord;
  }

  public static Jinx getInstance() {
    return instance;
  }
}
