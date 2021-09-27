package net.heavin.StaffEssentials.GUIs.Ban;

import org.bukkit.Bukkit;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

import net.heavin.StaffEssentials.Managers.Methods;

public class Responder extends StringPrompt{
	@Override
	public Prompt acceptInput(ConversationContext arg0, String arg1) {
		String p = arg0.getForWhom().toString();
		p = p.replace("CraftPlayer{name=", "");
		p = p.replace("}", "");
		arg0.getForWhom().sendRawMessage(Methods.color("&aSuccessfully Set custom ban reason as \"" + arg1 + "\""));
		if (BanGUI.banreason.containsKey(Bukkit.getPlayer(p).getUniqueId()));
		BanGUI.banreason.put(Bukkit.getPlayer(p).getUniqueId(), arg1);
		return new ResponderTwo();
	}

	@Override
	public String getPromptText(ConversationContext arg0) {
		String p = arg0.getForWhom().toString();
		p = p.replace("CraftPlayer{name=", "");
		p = p.replace("}", "");
		return Methods.color("&eType a custom ban reason for " + BanGUI.target.get(Bukkit.getPlayer(p)));
	}
	

}
