package net.heavin.StaffEssentials.GUIs.Ban;

import org.bukkit.Bukkit;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;

import net.heavin.StaffEssentials.Managers.Methods;


public class ResponderTwo extends MessagePrompt{

	@Override
	public String getPromptText(ConversationContext con) {
		String p = con.getForWhom().toString();
		p = p.replace("CraftPlayer{name=", "");
		p = p.replace("}", "");
		BanGUI.confirmMenu(Bukkit.getPlayer(p));
		return Methods.color("&aPlease confirm the ban on the gui");
	}

	@Override
	protected Prompt getNextPrompt(ConversationContext arg0) {
		// TODO Auto-generated method stub
		return Prompt.END_OF_CONVERSATION;
	}

}
