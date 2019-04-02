package com.penguinz.BloxEngine.data.gameObjects;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector2f;

import com.penguinz.BloxEngine.data.assets.TilemapAsset;
import com.penguinz.BloxEngine.data.materials.Texture;

public class Tilemap extends GameObject {

	private Vector2f startPos;
	private int tileSize;
	
	private HashMap<Integer, Texture> textures;
	
	private HashMap<Vector2f, Integer> map;
	
	public Tilemap(TilemapAsset asset) {
		this.startPos = new Vector2f(asset.getStartPos().x, asset.getStartPos().y);
		this.textures = new HashMap<Integer, Texture>();
		this.map = new HashMap<Vector2f, Integer>();
		this.tileSize = asset.getTileSize();
		
		for(Vector2f position: asset.getMap().keySet())
			map.put(position, asset.getMap().get(position));
		
		for(int num: asset.getDefinitions().keySet())
			textures.put(num, new Texture(asset.getDefinitions().get(num).getTexture(), "png"));
	}
	
	public Vector2f getStartPos() {
		return startPos;
	}
	
	public HashMap<Integer, Texture> getTextures() {
		return textures;
	}
	
	public HashMap<Vector2f, Integer> getMap() {
		return map;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
}
