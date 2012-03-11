package com.rscript.minecraft.plugins.HMSPlugin;


import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.rscript.minecraft.plugins.HMSPlugin.commands.ICommand;
import com.rscript.minecraft.plugins.HMSPlugin.commands.Experience;
import com.rscript.minecraft.plugins.HMSPlugin.commands.Hardcore;
import com.rscript.minecraft.plugins.HMSPlugin.commands.Inventory;
import com.rscript.minecraft.plugins.HMSPlugin.commands.Mute;
import com.rscript.minecraft.plugins.HMSPlugin.commands.Nick;
import com.rscript.minecraft.plugins.HMSPlugin.commands.Unmute;

public class HMSPlugin extends JavaPlugin{
	private static Logger log;
	
	public HardcoreList list = new HardcoreList();
	
	Inventory inventory = new Inventory(this);
	Experience experience = new Experience(this);
	Mute mute = new Mute(this);
	Unmute unmute = new Unmute(this);
	Hardcore hardcore = new Hardcore(this);
	
	ICommand[] cmd = new ICommand[6];
		
	
	HMSPlayerListener playerListener = new HMSPlayerListener(this);
	HMSEntityListener entityListener = new HMSEntityListener(this);
	
	
	public HMSPlugin() {
		cmd[0] = new Inventory(this);
		cmd[1] = new Experience(this);
		cmd[2] = new Mute(this);
		cmd[3] = new Unmute(this);
		cmd[4] = new Hardcore(this);
		cmd[5] = new Nick(this);
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
		this.getServer().getPluginManager().registerEvents(entityListener, this);

		
		
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
				if(player.hasPermission(command.getPermission())) {
					command.processCommand(player, args);
				}
				return true;
			}
		}

		return false;
	}


}
