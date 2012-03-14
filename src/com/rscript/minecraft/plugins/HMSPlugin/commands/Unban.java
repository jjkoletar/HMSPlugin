package com.rscript.minecraft.plugins.HMSPlugin.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.bukkit.entity.Player;

public class Unban implements ICommand {

	public void processCommand(Player player, String[] args) {
		if(args.length != 1) {
			player.sendMessage("Too much!");
			return;
		}
		
		try {
			URL url = new URL("http://hawksservers.com/webadmin/unban.php?uname=" + URLEncoder.encode(args[0], "UTF-8"));
			InputStream stream = url.openStream();
			stream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return "unban";
	}

	public boolean usesPermissions() {
		// TODO Auto-generated method stub
		return true;
	}

}
