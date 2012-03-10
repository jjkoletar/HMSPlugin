package com.rscript.minecraft.plugins.HMSPlugin.commands;

import org.bukkit.entity.Player;

import com.rscript.minecraft.plugins.HMSPlugin.HMSPlugin;

public class Nick implements ICommand {

	private HMSPlugin plugin;
	
	public Nick(HMSPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void processCommand(Player player, String[] args) {
		if(args.length == 0) {
			return;
		}
		if(args.length == 1) {
			if(args[0].length() > 16) 
				return;
			if(args[0].toLowerCase().contains("reset")) {
				player.setDisplayName(player.getName());
			}
			args[0] = args[0].replace(";", "§");
			player.setDisplayName(args[0]);
			return;
		}
		if(args.length == 2) {
			if(args[1].length() > 16) 
				return;
			Player nicked = plugin.getServer().getPlayer(args[0]);
			if(nicked == null)
				return;
			if(args[1].toLowerCase().contains("reset")) {
				nicked.setDisplayName(nicked.getName());
			}
			nicked.setDisplayName(args[1]);
		}
	}

	public void onEnable() {
	}

	public void onDisable() {

	}

	public String getCommand() {
		// TODO Auto-generated method stub
		return "nick";
	}

	public String getPermission() {
		// TODO Auto-generated method stub
		return "hawk.nick";
	}

}
