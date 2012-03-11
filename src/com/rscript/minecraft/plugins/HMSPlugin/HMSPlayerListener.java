package com.rscript.minecraft.plugins.HMSPlugin;


import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.rscript.minecraft.plugins.HMSPlugin.commands.Mute;

public class HMSPlayerListener implements Listener {
	HMSPlugin plugin;
	
	public HMSPlayerListener(HMSPlugin plugin) {
		this.plugin = plugin;
	}
	
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		if(Mute.muted.contains(event.getPlayer().getName())) {
			event.getPlayer().sendMessage(ChatColor.DARK_RED.toString() + "You are muted!");
			event.setCancelled(true);
		}
		event.setMessage(event.getMessage().replace(event.getPlayer().getName().toLowerCase(), event.getPlayer().getDisplayName()));
	}
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		if(event.isCancelled()) {
			return;
		}
		plugin.getServer().getLogger().info(event.getPlayer().getName() + " used command: " + event.getMessage());
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(event.getPlayer().getLocation().getWorld().getName().contains("Adventure")) {
			if(plugin.list.isDead(event.getPlayer().getName())) {
				event.getPlayer().teleport(plugin.getServer().getWorld("World").getSpawnLocation());
				event.getPlayer().sendMessage(ChatColor.BLUE.toString() + "You are dead in the hardcore world!");
			}
		}
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		if(event.getTo().getWorld().getName().contains("Adventure")) {
			if(plugin.list.isDead(event.getPlayer().getName())) {
				event.setTo(plugin.getServer().getWorld("World").getSpawnLocation());
				event.getPlayer().sendMessage(ChatColor.BLUE.toString() + "You are dead in the hardcore world!");
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if(event.getRespawnLocation().getWorld().getName().contains("Adventure")) {
			if(plugin.list.isDead(event.getPlayer().getName())) {
				event.setRespawnLocation(plugin.getServer().getWorld("World").getSpawnLocation());
				event.getPlayer().sendMessage(ChatColor.BLUE.toString() + "You are dead in the hardcore world!");
			}
		}
	}
	

}
