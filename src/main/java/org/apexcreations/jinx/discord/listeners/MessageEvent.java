package org.apexcreations.jinx.discord.listeners;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.SubscribeEvent;
import org.bukkit.Bukkit;

public class MessageEvent {

  @SubscribeEvent
  public void onChat(GuildMessageReceivedEvent event) {
    if (event.getAuthor().getId().equalsIgnoreCase(event.getJDA().getSelfUser().getId())) {
      return;
    }
    String message =
        event.getMember().getEffectiveName() + " says " + event.getMessage().getContentDisplay()
            + " in #" + event.getChannel().getName();
    Bukkit.broadcastMessage(message);
  }
}
