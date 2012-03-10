package com.rscript.minecraft.plugins.HMSPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import org.bukkit.inventory.ItemStack;

@SuppressWarnings("serial")
public class InventoryObject implements Serializable {
	
	private HashMap<String, ItemStack[]> inventory = new HashMap<String, ItemStack[]>();
	private HashMap<String, ItemStack[]> armorslots = new HashMap<String, ItemStack[]>();
	
	public void save() {
		File file = new File("plugins/HMSPlugin/savedinventories.dat");
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("plugins/HMSPlugin/savedinventories.dat"));
			oos.writeObject(this);
			oos.flush();
			oos.close();
			//Handle I/O exceptions
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ItemStack[][] getSavedInventory(String name) {
		ItemStack[] tempItem = inventory.get(name);
		ItemStack[] tempArmor = armorslots.get(name);
		ItemStack[][] toReturn = new ItemStack[2][];
		toReturn[0] = tempItem;
		toReturn[1] = tempArmor;
		return toReturn;
	}

	
	public void addInventory(String name, ItemStack[] items, ItemStack[] armor) {
		inventory.put(name, items);
		armorslots.put(name, armor);
	}
	
	
}
