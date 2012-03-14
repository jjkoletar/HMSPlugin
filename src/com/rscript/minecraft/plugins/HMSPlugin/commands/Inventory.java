package com.rscript.minecraft.plugins.HMSPlugin.commands;

import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Inventory implements ICommand {
	
	HashMap<String, String> currentlyOn = new HashMap<String, String>();	
	HashMap<String, ItemStack[]> oldInventories = new HashMap<String, ItemStack[]>();
	HashMap<String, ItemStack[]> oldArmor = new HashMap<String, ItemStack[]>();
	
	private JavaPlugin plugin;
	
	public Inventory(JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public void processCommand(Player player, String[] args) {
		if(args.length == 0) {
			player.sendMessage(ChatColor.BLUE.toString() + "Inventory Edit:");
			player.sendMessage(ChatColor.BLUE.toString() + "Use the /inv see command to see another player's inventory.");
			player.sendMessage(ChatColor.BLUE.toString() + "Use /inv switch to give them your modified inventory.");
			player.sendMessage(ChatColor.BLUE.toString() + "Use /inv end to end editing without modifying their inventory.");
			return;
		}
		if(args[0].equalsIgnoreCase("see")) {
			if(player.hasPermission("inventory.see")) {
				handleSee(player, args);
			}
		}
		else if (args[0].equalsIgnoreCase("end")) {
			if (player.hasPermission("inventory.switch")) {
				handleEnd(player);
			}
		}
		else if (args[0].equalsIgnoreCase("switch")) {
			handleSwitch(player);
		}
	}
	
	protected void handleSee(Player player, String[] args) {
		if(args.length < 2){
			player.sendMessage(ChatColor.BLUE.toString() + "Usage: /inv see [username]");
			return;
		}
		Player seen = plugin.getServer().getPlayer(args[1]);
		
		if (seen == null) {
			player.sendMessage(ChatColor.BLUE.toString() + args[1] + ": Player does not exist!");
			return;
		}
		currentlyOn.put(player.getName(), seen.getName());
		oldInventories.put(player.getName(), player.getInventory().getContents());
		oldArmor.put(player.getName(), player.getInventory().getArmorContents());
		
		player.getInventory().setContents(seen.getInventory().getContents());
		player.getInventory().setArmorContents(seen.getInventory().getArmorContents());
		player.sendMessage(ChatColor.BLUE.toString()+"You are now viewing the inventory of: " + ChatColor.WHITE.toString() + seen.getName());
	}
	
	protected void handleEnd(Player player) {
		ItemStack[] stack = oldInventories.get(player.getName());
		ItemStack[] armor = oldArmor.get(player.getName());
		
		if (stack == null || armor == null)
			return;
		player.getInventory().setContents(stack);
		player.getInventory().setArmorContents(armor);
		oldInventories.remove(player.getName());
		currentlyOn.remove(player.getName());
	}
	
	protected void handleSwitch(Player player) {
		String name = currentlyOn.get(player.getName());
		
		if (name == null)
			return;
		
		Player otherplayer = plugin.getServer().getPlayer(name);
		
		if (otherplayer == null)
			return;
		
		otherplayer.getInventory().setContents(player.getInventory().getContents());
		otherplayer.getInventory().setArmorContents(player.getInventory().getArmorContents());
		handleEnd(player);
		player.sendMessage(ChatColor.BLUE.toString()+"You have switched inventory with: " + ChatColor.WHITE.toString() + otherplayer.getName());
	}
	

	public void onEnable() {
	}

	public void onDisable() {
		Iterator<String> iterator = currentlyOn.keySet().iterator();
		while(iterator.hasNext()) {
			Player player = plugin.getServer().getPlayer(iterator.next());
			handleEnd(player);
		}
		
		oldInventories.clear();
		oldArmor.clear();
		currentlyOn.clear();
			}

	public String getCommand() {
		// TODO Auto-generated method stub
		return "inv";
	}

	public boolean usesPermissions() {
		// TODO Auto-generated method stub
		return true;
	}


	
	
}
