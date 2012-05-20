package com.rscript.minecraft.plugins.HMSPlugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Cake implements ICommand {

	public void processCommand(Player player, String[] args) {
		if(player.getInventory().firstEmpty() == -1) {
			player.sendMessage(ChatColor.RED.toString() + "You need to make room in your inventory. :(");
		}
		player.sendMessage(ChatColor.GOLD.toString() + "Enjoy your delicious cake!");
		player.getInventory().addItem(new ItemStack(Material.CAKE, 1));
	}

	public void onEnable() {
		// TODO Auto-generated method stub
		
	}

	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

	public String getCommand() {
		// TODO Auto-generated method stub
		return "cake";
	}

	public boolean usesPermissions() {
		// TODO Auto-generated method stub
		return true;
	}

}
