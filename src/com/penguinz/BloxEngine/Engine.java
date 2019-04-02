package com.penguinz.BloxEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.penguinz.BloxEngine.data.gameObjects.Camera;
import com.penguinz.BloxEngine.data.gameObjects.GameObject;
import com.penguinz.BloxEngine.data.gameObjects.Tilemap;
import com.penguinz.BloxEngine.data.gameObjects.components.Component.ComponentType;
import com.penguinz.BloxEngine.data.gameObjects.components.RigidBody;
import com.penguinz.BloxEngine.graphics.Renderer;
import com.penguinz.BloxEngine.physics.PhysicsEngine;

public class Engine {

	private PhysicsEngine physicsEngine;
	private Renderer renderer;
	private Assets assets;
	
	private HashMap<String, GameObject> gameObjects;
	
	public Engine() {
		this.physicsEngine = new PhysicsEngine();
		this.renderer = new Renderer();
		this.gameObjects = new HashMap<String, GameObject>();
		this.assets = new Assets("assets");
	}
	
	public void update() {
		for(String gameObject: gameObjects.keySet()) {
			if(gameObjects.get(gameObject) instanceof Camera) {
				((Camera) gameObjects.get(gameObject)).update();
			}
		}
		List<RigidBody> rigidBodies = new ArrayList<RigidBody>();
		for(String gameObject: gameObjects.keySet()) {
			if(gameObjects.get(gameObject).hasComponent(ComponentType.RIGID_BODY)) {
				rigidBodies.add((RigidBody)gameObjects.get(gameObject).getComponent(ComponentType.RIGID_BODY));
			}
		}
		physicsEngine.update(rigidBodies, 1);
	}
	
	public void render(String cameraName) {
		List<GameObject> renderObjects = new ArrayList<GameObject>();
		List<Tilemap> tilemaps = new ArrayList<Tilemap>();
		Camera camera;
		if(gameObjects.get(cameraName) instanceof Camera)
			camera = (Camera) gameObjects.get(cameraName);
		else {
			System.err.println(cameraName+" is not a camera game object!");
			return;
		}
		for(String object: gameObjects.keySet()) {
			GameObject gameObject = gameObjects.get(object);
			if(gameObject.hasComponent(ComponentType.MESH_RENDERER)) {
				renderObjects.add(gameObject);
			} else if(gameObject.hasComponent(ComponentType.TILEMAP_RENDERER)) {
				tilemaps.add((Tilemap) gameObject);
			}
		}
		renderer.prepare(camera);
		renderer.renderTilemaps(camera, tilemaps);
		renderer.render(camera, renderObjects);
	}
	
	public GameObject addGameObject(String name, GameObject gameObject) {
		gameObjects.put(name, gameObject);
		return gameObject;
	}
	
	public GameObject getGameObject(String name) {
		return gameObjects.get(name);
	}
	
	public Assets getAssets() {
		return assets;
	}
	
}
