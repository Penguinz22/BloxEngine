package com.penguinz.BloxEngine.data.assets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.penguinz.BloxEngine.Assets;

public class TileAsset extends Asset {

	private String texture;
	private int size;
	
	public TileAsset(Assets assets, String filePath) {
		super(filePath);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			
			String line;
			while((line=reader.readLine())!=null) {
				if(line.startsWith("Image:")) {
					line = line.substring(6, line.length());
					line = line.trim();
					this.texture = assets.getAssetsFolder()+"/"+line.substring(1, line.length()-1);
				} else if(line.startsWith("Size:")) {
					line = line.substring(5, line.length());
					this.size = Integer.valueOf(line.trim());
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("File: "+filePath+" was not found!");
		} catch (IOException e) {
			System.err.println("Error closing file reader!");
		}
		
	}
	
	public String getTexture() {
		return texture;
	}
	
	public int getSize() {
		return size;
	}
	
}
