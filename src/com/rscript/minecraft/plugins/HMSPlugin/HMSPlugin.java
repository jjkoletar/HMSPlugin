package com.rscript.minecraft.plugins.HMSPlugin;


import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.rscript.minecraft.plugins.HMSPlugin.commands.Ban;
import com.rscript.minecraft.plugins.HMSPlugin.commands.Cake;
import com.rscript.minecraft.plugins.HMSPlugin.commands.ICommand;
import com.rscript.minecraft.plugins.HMSPlugin.commands.Experience;
import com.rscript.minecraft.plugins.HMSPlugin.commands.Inventory;
import com.rscript.minecraft.plugins.HMSPlugin.commands.Mute;
import com.rscript.minecraft.plugins.HMSPlugin.commands.Nick;
import com.rscript.minecraft.plugins.HMSPlugin.commands.Unban;
import com.rscript.minecraft.plugins.HMSPlugin.commands.Unmute;

public class HMSPlugin extends JavaPlugin{
	private static Logger log;
	
	
	Inventory inventory = new Inventory(this);
	Experience experience = new Experience(this);
	Mute mute = new Mute(this);
	Unmute unmute = new Unmute(this);
	
	ArrayList<ICommand> cmd = new ArrayList<ICommand>();
		
	
	HMSPlayerListener playerListener = new HMSPlayerListener(this);
	
	
	public HMSPlugin() {
		cmd.add(new Inventory(this));
		cmd.add(new Experience(this));
		cmd.add(new Mute(this));
		cmd.add(new Unmute(this));
		cmd.add(new Nick(this));
		cmd.add(new Ban());
		cmd.add(new Unban());
		cmd.add(new Cake());
	}
	
	public void onDisable() {
		// TODO Auto-generated method stub		
		log.info("Inventory: Disabled!");
		this.getServer().getScheduler().cancelTasks(this);
		inventory.onDisable();
		
		for(ICommand command: cmd) {
			command.onDisable();
		}
	}

	public void onEnable() {
		PluginDescriptionFile pdf = this.getDescription();
		
		this.getServer().getPluginManager().registerEvents(playerListener, this);

		
		
		for(ICommand command: cmd) {
			command.onEnable();
		}
				
		// TODO Auto-generated method stub
		log = Logger.getLogger("Minecraft");		
		log.info(pdf.getName() + " version " + pdf.getVersion() + " loaded!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		for (ICommand command : this.cmd) {
			if (command.getCommand().equalsIgnoreCase(cmd.getName())) {
				if (!(sender instanceof Player))
					return true;

				Player player = (Player) sender;
				if(command.usesPermissions() == false) {
					command.processCommand(player, args);
					return true;
				}
				if(player.hasPermission("hawk." + cmd.getName())) {
					command.processCommand(player, args);
				}
				return true;
			}
		}

		return false;
	}


}
