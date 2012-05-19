package com.rscript.minecraft.plugins.HMSPlugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Ban implements ICommand {

	public void processCommand(Player player, String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 0){
			player.sendMessage("/ban <username> [reason]");
			return;
		}
		if(args.length == 1) {
			try {
				URL url = new URL("http://hawksservers.com/webadmin/banUser.php?" + "uname=" + URLEncoder.encode(args[0], "UTF-8") + "&reason=" + URLEncoder.encode("You are banned on this server!", "UTF-8"));
				url.openStream();
				player.sendMessage(ChatColor.DARK_RED.toString() + "You have banned: " + args[0]);
				player.getServer().broadcastMessage(ChatColor.DARK_RED.toString() + args[0] + " has been banned by " + ChatColor.DARK_BLUE.toString() + player.getDisplayName() + "!");
				if(player.getServer().getPlayerExact(args[0]) != null) {
					player.getServer().getPlayerExact(args[0]).kickPlayer("You are banned on this server!");
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				StringBuilder sb = new StringBuilder();
				String name = args[0];
				args[0] = "";
				for (String s : args) {
					sb.append(s);
					sb.append(" ");
				}
				sb.deleteCharAt(sb.length()-1);
				
				URL url = new URL("http://hawksservers.com/webadmin/banUser.php?uname=" + URLEncoder.encode(name, "UTF-8") +"&reason=" + URLEncoder.encode(sb.toString(), "UTF-8"));
				url.openStream();
				player.getServer().broadcastMessage(ChatColor.DARK_RED.toString() + name + " was banned by " + ChatColor.DARK_BLUE.toString() + player.getDisplayName() + ChatColor.DARK_RED.toString() + " for" + ChatColor.AQUA.toString() + sb.toString());
				if(player.getServer().getPlayerExact(name) != null) {
					player.getServer().getPlayerExact(name).kickPlayer(sb.toString());
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		return "ban";
	}

	public boolean usesPermissions() {
		// TODO Auto-generated method stub
		return true;
	}

}
