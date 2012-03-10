package com.rscript.minecraft.plugins.HMSPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class HardcoreList implements Serializable {

	private ArrayList<String> list = new ArrayList<String>();
	
	
	public void clearList() {
		list.clear();
	}
	
	public void add(String name) {
		list.add(name);
	}
	
	public void remove(String name) {
		list.remove(name);
	}
	
	public boolean isDead(String name) {
		return list.contains(name);
	}
	
	public String[] all() {
		return (String[]) list.toArray();
	}
	
	public void save() {
		File file = new File("plugins/HMSPlugin/hardcore.dat");
		if(!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("plugins/HMSPlugin/hardcore.dat"));
			oos.writeObject(this);
			oos.flush();
			oos.close();
			//Handle I/O exceptions
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
