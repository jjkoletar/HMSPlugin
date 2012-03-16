package com.rscript.minecraft.plugins.HMSPlugin;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;

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
	public void onPlayerLogin(PlayerLoginEvent event) {
	try {
			URL url = new URL("http://hawksservers.com/webadmin/isBanned.php?uname=" + URLEncoder.encode(event.getPlayer().getName(), "UTF-8"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String result = reader.readLine();
			if(Boolean.parseBoolean(result) || result == "tempbanned") {
				reader.close();
				url = new URL("http://hawksservers.com/webadmin/getBanReason.php?uname=" + URLEncoder.encode(event.getPlayer().getName(), "UTF-8") + "&type=" + URLEncoder.encode(result, "UTF-8"));
				reader = new BufferedReader(new InputStreamReader(url.openStream()));
				event.disallow(PlayerLoginEvent.Result.KICK_BANNED,  "You are banned: " + reader.readLine());
				reader.close();
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
