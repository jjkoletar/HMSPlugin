package com.rscript.minecraft.plugins.HMSPlugin.commands;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Unban implements ICommand {

	public void processCommand(Player player, String[] args) {
		if(args.length != 1) {
			player.sendMessage("Usage: /unban [username]");
			return;
		}
		
		try {
			URL url = new URL("http://hawksservers.com/webadmin/unban.php?uname=" + URLEncoder.encode(args[0], "UTF-8"));
			InputStream stream = url.openStream();
			player.sendMessage(ChatColor.DARK_RED.toString() + "Player unbanned!");
			player.getServer().broadcastMessage(ChatColor.AQUA.toString() + args[0] + ChatColor.DARK_RED.toString() + " has been unbanned by " + ChatColor.DARK_BLUE.toString() + player.getDisplayName());
			stream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onEnable() {
		// TODO Auto-generated method stub
		
	}

	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	public String getCommand() {
		// TODO Auto-generated method stub
		return "unban";
	}

	public boolean usesPermissions() {
		// TODO Auto-generated method stub
		return true;
	}

}
