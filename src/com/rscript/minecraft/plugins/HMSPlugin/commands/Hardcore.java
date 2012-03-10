package com.rscript.minecraft.plugins.HMSPlugin.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.rscript.minecraft.plugins.HMSPlugin.HMSPlugin;

public class Hardcore implements ICommand {
	
	public static ArrayList<String> dead = new ArrayList<String>();
	private HMSPlugin plugin;
	
	public Hardcore(HMSPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void processCommand(Player player, String[] args) {
		if(player.hasPermission("hawk.hardcore")) {
		if(args.length != 2)
			return;
		if(args[0].equalsIgnoreCase("unban")) {
			plugin.list.remove(args[1]);
			player.sendMessage(ChatColor.BLUE.toString() + "Player has been revived!");
		}
		if(args[0].equalsIgnoreCase("kill")) {
			plugin.list.add(args[1]);
			player.sendMessage(ChatColor.BLUE.toString() + "Player has been killed!");
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
		return "hardcore";
	}

	public String getPermission() {
		// TODO Auto-generated method stub
		return null;
	}

}
