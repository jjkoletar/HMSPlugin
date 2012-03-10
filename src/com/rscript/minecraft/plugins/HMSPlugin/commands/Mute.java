package com.rscript.minecraft.plugins.HMSPlugin.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.rscript.minecraft.plugins.HMSPlugin.HMSPlugin;
import com.rscript.minecraft.plugins.HMSPlugin.UnmuteTask;

public class Mute implements ICommand {
	
	public static ArrayList<String> muted = new ArrayList<String>();
	private HMSPlugin plugin;
	
	public Mute(HMSPlugin plugin) {
		this.plugin = plugin;
	}

	public void processCommand(Player player, String[] args) {
		if(args.length == 0) {
			player.sendMessage(ChatColor.BLUE.toString() + "Usage: /mute [username] [time]");
			return;
		}
		if (args.length == 1) {
			muted.add(args[0].toLowerCase());
			player.sendMessage(ChatColor.BLUE.toString() + "Muted!");
		}
		if(args.length == 3) {
			try {
				int multiplier = 1;
			    int time = Integer.parseInt(args[1]);
			    if (args[2].toLowerCase().contains("seconds")) {
			    	multiplier = 20;
			    }
			    else if (args[2].toLowerCase().contains("minutes")) {
			    	multiplier = 20*60;
			    }
			    else if (args[2].toLowerCase().contains("hours")) {
			    	multiplier = 20*60*60;
			    }
			    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new UnmuteTask(args[0], plugin), Long.valueOf(multiplier*time).longValue());
			    muted.add(args[0].toLowerCase());
			}
			catch(NumberFormatException exception) {
				player.sendMessage("you suck");
				return;
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
		return "mute";
	}

	public String getPermission() {
		// TODO Auto-generated method stub
		return "hawk.mute";
	}

}
