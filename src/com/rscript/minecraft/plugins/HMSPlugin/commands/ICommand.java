package com.rscript.minecraft.plugins.HMSPlugin.commands;

import org.bukkit.entity.Player;

public interface ICommand {
		public void processCommand(Player player, String[] args);
		
		public void onEnable();
		public void onDisable();
		
		public String getCommand();
		public String getPermission();
}
