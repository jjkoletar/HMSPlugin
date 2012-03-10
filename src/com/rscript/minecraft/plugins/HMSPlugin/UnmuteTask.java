package com.rscript.minecraft.plugins.HMSPlugin;

import org.bukkit.ChatColor;

import com.rscript.minecraft.plugins.HMSPlugin.commands.Mute;

public class UnmuteTask implements Runnable {
	private HMSPlugin plugin;
	private String playername;
	
	public UnmuteTask(String name, HMSPlugin plugin) {
		this.playername = name;
		this.plugin = plugin;
	}
	
	public void run() {
		Mute.muted.remove(playername);
		if(plugin.getServer().getPlayer(playername) != null) {
			plugin.getServer().getPlayer(playername).sendMessage(ChatColor.BLUE.toString() + "You have been unmuted!");
		}
	}

}
