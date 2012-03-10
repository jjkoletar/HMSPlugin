package com.rscript.minecraft.plugins.HMSPlugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Experience implements ICommand {

	private JavaPlugin plugin;
	
	public Experience(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public void processCommand(Player player, String[] args) {
		if(args.length == 0) {
			player.sendMessage(ChatColor.BLUE.toString() + "exp: Sets a users experience level.");
			player.sendMessage(ChatColor.BLUE.toString() + "Usage:");
			player.sendMessage(ChatColor.BLUE.toString() + "/exp <username> [level]");
			return;
		}
		if(args.length == 1) {
			try {
			    int levelToSet = Integer.parseInt(args[0]);
				player.setLevel(levelToSet);
			}
			catch (NumberFormatException exception) {
				player.sendMessage(ChatColor.BLUE.toString() + args[0] + ": Not a valid experience level!");
			}
		}
		if(args.length == 2) {
			Player toSet = plugin.getServer().getPlayer(args[0]);
			if(toSet == null) {
				player.sendMessage(ChatColor.BLUE.toString() + "Player does not exist!");
				return;
			}
			try {
			    int levelToSet = Integer.parseInt(args[1]);
			    toSet.setLevel(levelToSet);
			}
			catch (NumberFormatException exception) {
				player.sendMessage(ChatColor.BLUE.toString() + args[0] + ": Not a valid experience level!");
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
		return "exp";
	}

	public String getPermission() {
		// TODO Auto-generated method stub
		return "hawk.experience";
	}

	
	
}
