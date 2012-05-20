package com.rscript.minecraft.plugins.HMSPlugin.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import org.bukkit.entity.Player;

import com.rscript.minecraft.plugins.HMSPlugin.HMSPlugin;

public class Nick implements ICommand {

	private HMSPlugin plugin;
	public static HashMap<String, String> nicknames = new HashMap<String, String>();
	
	public Nick(HMSPlugin plugin) {
		this.plugin = plugin;
	}
	
	public void processCommand(Player player, String[] args) {
		if(args.length == 0) {
			return;
		}
		if(args.length == 1) {
			if(args[0].length() > 16) 
				return;
			if(args[0].toLowerCase().contains("reset")) {
				nicknames.remove(player.getName().toLowerCase());
				player.setDisplayName(player.getName());
				return;
			}
			args[0] = args[0].replaceAll("(&([a-f0-9k-orR]))", "\u00A7$2");
			nicknames.put(player.getName().toLowerCase(), args[0]);
			player.setDisplayName(args[0]);
			return;
		}
		if(args.length == 2) {
			if(args[1].length() > 16) 
				return;
			Player nicked = plugin.getServer().getPlayer(args[0]);
			if(nicked == null)
				return;
			if(args[1].toLowerCase().contains("reset")) {
				nicked.setDisplayName(nicked.getName());
				return;
			}
			args[1] = args[1].replaceAll("(&([a-f0-9k-orR]))", "\u00A7$2");
			nicknames.put(nicked.getName().toLowerCase(), args[1]);
			nicked.setDisplayName(args[1]);
		}
	}

	public void onEnable() {
		try {
			Object object = (new ObjectInputStream(new FileInputStream(new File("Nicknames.dat")))).readObject();
			if(object instanceof HashMap<?, ?>) {
				nicknames = (HashMap<String, String>) object;
			}
			else {
				System.out.println("HMSPlugin: Nickname file broken!");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("HMSPlugin: Couldn't load nicknames!");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("HMSPlugin: Couldn't load nicknames!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("HMSPlugin: Couldn't load nicknames!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onDisable() {
		try {
			ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(new File("Nicknames.dat")));
			stream.writeObject(nicknames);
			stream.close();
		} catch (FileNotFoundException e) {
			System.out.println("HMSPlugin: Couldn't save nicknames!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("HMSPlugin: Couldn't save nicknames!");
			e.printStackTrace();
		}
	}

	public String getCommand() {
		// TODO Auto-generated method stub
		return "nick";
	}

	public boolean usesPermissions() {
		// TODO Auto-generated method stub
		return true;
	}

}
