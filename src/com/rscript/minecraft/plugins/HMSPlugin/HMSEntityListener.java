package com.rscript.minecraft.plugins.HMSPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class HMSEntityListener implements Listener{

	private HMSPlugin plugin;
	
	public HMSEntityListener(HMSPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		/*if(event.getEntity().getLocation().getWorld().getName().contains("Adventure")) {
			Player player = null;
			if(event.getEntity() instanceof Player){
				player = (Player)event.getEntity();
			    plugin.list.add(player.getName());
			    plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new HardcoreTask(this.plugin, player.getName()), 12000);
			}
			else
				return;
		}
		*/
	}

	
}
