package com.rscript.minecraft.plugins.HMSPlugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HardcoreTask implements Runnable{
	
	private HMSPlugin plugin;
	private String name;
	
	public HardcoreTask(HMSPlugin plugin, String name) {
		this.plugin = plugin;
		this.name = name;
	}

	public void run() {
		if (!plugin.list.isDead(name)) {
			plugin.list.remove(name);
			if (plugin.getServer().getPlayer(name) != null) {
				Player player = plugin.getServer().getPlayer(name);
				player.sendMessage(ChatColor.BLUE.toString()
						+ "You are no longer dead in the hardcore world!");
			}
		}
	}

}
