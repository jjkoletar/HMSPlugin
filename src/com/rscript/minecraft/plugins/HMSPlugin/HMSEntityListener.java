package com.rscript.minecraft.plugins.HMSPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

public class HMSEntityListener extends EntityListener{

	private HMSPlugin plugin;
	
	public HMSEntityListener(HMSPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void onEntityDeath(EntityDeathEvent event) {
		if(event.getEntity().getLocation().getWorld().getName().contains("Adventure")) {
			Player player = null;
			if(event.getEntity() instanceof Player){
				player = (Player)event.getEntity();
			    plugin.list.add(player.getName());
			    plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new HardcoreTask(this.plugin, player.getName()), 12000);
			}
			else
				return;
		}
	}

	
}
