package com.penguinz.BloxEngine;

import java.util.ArrayList;
import java.util.List;

import com.penguinz.BloxEngine.data.assets.Asset;
import com.penguinz.BloxEngine.data.assets.TileAsset;
import com.penguinz.BloxEngine.data.assets.TilemapAsset;

public class Assets {

	private List<Asset> assets;
	private String assetsFolder;
	
	public Assets(String assetsFolder) {
		this.assets = new ArrayList<Asset>();
		this.assetsFolder = assetsFolder;
	}
	
	public TileAsset addTile(String filePath) {
		TileAsset tile = new TileAsset(this, assetsFolder+"/"+filePath);
		assets.add(tile);
		return tile;
	}
	
	public TilemapAsset addTilemap(String filePath) {
		TilemapAsset tilemap = new TilemapAsset(this, assetsFolder+"/"+filePath);
		assets.add(tilemap);
		return tilemap;
	}
	
	public Asset getAsset(String filePath) {
		for(Asset asset: assets) {
			if(asset.getFilePath().equals(assetsFolder+"/"+filePath))
				return asset;
		}
		System.err.println("Could not retrieve Asset: "+filePath);
		return null;
	}
	
	public String getAssetsFolder() {
		return assetsFolder;
	}
	
}
