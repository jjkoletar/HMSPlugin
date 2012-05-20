package com.rscript.minecraft.plugins.HMSPlugin;


import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.rscript.minecraft.plugins.HMSPlugin.commands.*;

public class HMSPlugin extends JavaPlugin{
	private static Logger log;
	
	
	
	ArrayList<ICommand> cmd = new ArrayList<ICommand>();
		
		
	
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
		log.info("HMSPlugin disabling.");
		this.getServer().getScheduler().cancelTasks(this);
		
		for(ICommand command: cmd) {
			command.onDisable();
		}
		log.info("HMSPlugin: Disabled.");
	}

	public void onEnable() {
		PluginDescriptionFile pdf = this.getDescription();
		
		this.getServer().getPluginManager().registerEvents(new HMSPlayerListener(this), this);
		this.getServer().getPluginManager().registerEvents(new HMSBlockListener(), this);

		
		
		for(ICommand command: cmd) {
			command.onEnable();
		}
				
		// TODO Auto-generated method stub
		log = getLogger();		
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
