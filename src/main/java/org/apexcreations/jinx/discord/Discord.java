package org.apexcreations.jinx.discord;

import java.util.HashSet;
import java.util.Set;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.hooks.IEventManager;
import org.apexcreations.jinx.discord.listeners.MessageEvent;

public class Discord {

  private final JDA jda;
  private final Guild guild;

  private Discord(JDA jda, Guild guild) {
    this.jda = jda;
    this.guild = guild;
  }

  public JDA getJda() {
    return this.jda;
  }

  public static class Builder {

    private final String discordToken;
    private boolean autoReconnect = true;
    private String game = "Jinx - Property of ApexCreations";
    private final String guildId;
    private IEventManager eventManager;
    private Set<Object> listeners = new HashSet<>();

    public Builder(String discordToken, IEventManager eventManager, String guildId) {
      this.discordToken = discordToken;
      this.guildId = guildId;
      this.eventManager = eventManager;
    }

    public Builder autoreconnect(boolean b) {
      this.autoReconnect = b;
      return this;
    }

    public Builder game(String game) {
      this.game = game;
      return this;
    }

    public Builder eventManager(IEventManager eventManager) {
      this.eventManager = eventManager;
      return this;
    }

    public Builder listener(Object object) {
      this.listeners.add(object);
      return this;
    }

    public Discord build() {
      try {
        JDA jda = new JDABuilder(AccountType.BOT)
            .setToken(discordToken)
            .setAutoReconnect(this.autoReconnect)
            .setEventManager(this.eventManager)
            .addEventListener(this.listeners)
            .setGame(Game.of(GameType.DEFAULT, game))
            .buildBlocking();
        return new Discord(jda, jda.getGuildById(this.guildId));
      } catch (LoginException | InterruptedException e) {
        e.printStackTrace();
       /* Discordian.getInstance().getLogger().severe("Could not load JDA");
        Discordian.getInstance().getServer().getPluginManager().disablePlugin(Discordian.getInstance());*/
      }
      return null;
    }
  }
}
