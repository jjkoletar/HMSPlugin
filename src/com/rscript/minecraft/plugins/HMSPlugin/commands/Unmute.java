package com.rscript.minecraft.plugins.HMSPlugin.commands;

import org.bukkit.entity.Player;

import com.rscript.minecraft.plugins.HMSPlugin.HMSPlugin;
import com.rscript.minecraft.plugins.HMSPlugin.UnmuteTask;

public class Unmute implements ICommand {
	private HMSPlugin plugin;
	
	public Unmute(HMSPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void processCommand(Player player, String[] args) {
		if(args.length == 1) {
			UnmuteTask task = new UnmuteTask(args[0], plugin);
			task.run();
			player.sendMessage("Player unmuted!");
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
		return "unmute";
	}

	public boolean usesPermissions() {
		// TODO Auto-generated method stub
		return true;
	}

}
