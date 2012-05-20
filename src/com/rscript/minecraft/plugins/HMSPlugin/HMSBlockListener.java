package com.rscript.minecraft.plugins.HMSPlugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class HMSBlockListener implements Listener {
	
	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		if (event.isCancelled()) return;
		for (int i = 0; i < 4; i++) {
			event.setLine(i, event.getLine(i).replaceAll("(&([a-f0-9k-orR]))", "\u00A7$2"));
		}
	}
}
