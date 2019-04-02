package com.penguinz.BloxEngine.data.assets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;

import com.penguinz.BloxEngine.Assets;

public class TilemapAsset extends Asset {

	private Vector2f startPos;
	
	private HashMap<Integer, TileAsset> tileDefinitions;
	
	private HashMap<Vector2f, Integer> tileMap;
	
	private int tileSize;
	
	public TilemapAsset(Assets assets, String filePath) {
		super(filePath);
		this.tileDefinitions = new HashMap<Integer, TileAsset>();
		this.tileMap = new HashMap<Vector2f, Integer>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			
			String line;
			boolean mapMode = false;
			int mapLines = 0;
			while((line=reader.readLine())!=null) {
				if(mapMode) {
					for(int i=0;i<line.split(",").length;i++) {
						if(line.split(",")[i].trim().isEmpty())
							continue;
						tileMap.put(new Vector2f(i+startPos.x, mapLines+startPos.y), Integer.valueOf(line.split(",")[i]));
					}
					mapLines++;
				} else if(line.startsWith("StartPos:")) {
					line = line.substring(9, line.length());
					line = line.trim().substring(1, line.trim().length()-1);
					this.startPos = new Vector2f(Integer.valueOf(line.split(",")[0].trim()), Integer.valueOf(line.split(",")[1].trim()));
				} else if(line.startsWith("TileSize:")) {
						line = line.substring(9, line.length());
						this.tileSize = Integer.valueOf(line.trim());
				} else if(line.startsWith("TileDef:")) {
					line = line.substring(8, line.length());
					line = line.trim().substring(1, line.trim().length()-1);
					tileDefinitions.put(Integer.valueOf(line.split(",")[0].trim()), assets.addTile(line.split(",")[1].trim().substring(1, line.split(",")[1].trim().length()-1)));
				} else if(line.startsWith("Tiles:")) {
					mapMode = true;
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("File: "+filePath+" was not found!");
		} catch (IOException e) {
			System.err.println("Error closing file reader!");
		}
	}
	
	public Vector2f getStartPos() {
		return startPos;
	}
	
	public HashMap<Integer, TileAsset> getDefinitions() {
		return tileDefinitions;
	}
	
	public HashMap<Vector2f, Integer> getMap() {
		return tileMap;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
}
