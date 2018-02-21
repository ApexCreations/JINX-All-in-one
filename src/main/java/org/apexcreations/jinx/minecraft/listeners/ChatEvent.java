package org.apexcreations.jinx.minecraft.listeners;

import org.apexcreations.jinx.Jinx;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

  @EventHandler
  public void onChat(AsyncPlayerChatEvent event) {
    if (event.isCancelled()) {
      return;
    }
    String message = event.getPlayer().getName() + " >> " + event.getMessage();
    Jinx.getInstance().getDiscord().getJda().getGuilds().get(0)
        .getTextChannelsByName("general", true)
        .get(0).sendMessage(message).queue();
  }
}
