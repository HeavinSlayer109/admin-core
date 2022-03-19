package cf.heavin.AdminCore.Moderation.Mute;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import cf.heavin.AdminCore.AdminCore;
import cf.heavin.AdminCore.Files.Mutes;
import cf.heavin.AdminCore.Managers.Methods;

public class onTalk implements Listener {
	Mutes muteConfig = AdminCore.getMutesConfig();
	@EventHandler
	public void onTalkEvent(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		if (muteConfig.getConfig().contains(player.getName())) {
			if (muteConfig.getConfig().getLong(player.getName()) > System.currentTimeMillis()) {
				e.setCancelled(true);
				long timeleft = (muteConfig.getConfig().getLong(player.getName()) - System.currentTimeMillis()) / 1000 + 1;
				player.sendMessage(Methods.color("&cYou are still muted for " + timeleft + "s"));
				return;
			}
		}
	}

}
