package com.penguinz.BloxEngine.testing;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.penguinz.BloxEngine.Engine;
import com.penguinz.BloxEngine.data.Transformation;
import com.penguinz.BloxEngine.data.gameObjects.Camera;
import com.penguinz.BloxEngine.data.gameObjects.GameObject;
import com.penguinz.BloxEngine.data.gameObjects.Tilemap;
import com.penguinz.BloxEngine.data.gameObjects.components.MeshRenderer;
import com.penguinz.BloxEngine.data.gameObjects.components.TilemapRenderer;
import com.penguinz.BloxEngine.data.materials.Material;
import com.penguinz.BloxEngine.data.materials.Texture;
import com.penguinz.BloxEngine.data.models.RectModel;
import com.penguinz.BloxEngine.graphics.Window;

public class Tester {
	
	public static void main(String[] args) {
		new Tester().start2();
	}
	
	public void start2() {
		Window.createWindow(1280, 720);
		
		Engine engine = new Engine();
		
		Tilemap tilemap = new Tilemap(engine.getAssets().addTilemap("Base.tilemap"));
		tilemap.addComponent(new TilemapRenderer(tilemap));
		engine.addGameObject("tilemap", tilemap);
		
		GameObject square = new GameObject(new Transformation(new Vector2f(50, 50), new Vector3f(0, 0, 0), new Vector2f(50f, 50f)));
		square.addComponent(new MeshRenderer(square, new RectModel(), new Material(new Texture("assets/Grass.png", "PNG"))));
		//engine.addGameObject("square", square);
		
		engine.addGameObject("camera", new Camera(1, new Vector3f(0f, 1, 1f)));
		
		while(!Window.isClosed()) {
			
			engine.update();
			
			engine.render("camera");
			
			Window.updateWindow(60);
		}
		
		Window.destroyWindow();
	}
	
//	public void start() {
//
//		
//		
//		
//		
//		GameObject floor = engine.addGameObject("floor", new GameObject(new Transformation(new Vector2f(0, 500), new Vector3f(0, 0, 0), new Vector2f(500f, 50f))));
//		floor.addComponent(new MeshRenderer(floor, new RectModel(), new Material(1f, 0, 0.5f)));
//		RigidBody floorRb = (RigidBody) floor.addComponent(new RigidBody(floor));
//		floorRb.hasGravity = false;
//		floor.addComponent(new PlaneCollider(floor, new Vector2f(0, 1)));
//		
//		GameObject square = engine.addGameObject("square", new GameObject(new Transformation(new Vector2f(50, 50), new Vector3f(0, 0, 0), new Vector2f(50f, 50f))));
//		square.addComponent(new MeshRenderer(square, new RectModel(), new Material(1, 1, 0.5f)));
//		RigidBody rb = (RigidBody) square.addComponent(new RigidBody(square));
//		rb.setVelocity(new Vector2f(0.5f, 0));
//		rb.hasGravity = true;
//		square.addComponent(new BoxCollider(square));
//		rb.bounce = 1;
//		
//		GameObject square2 = engine.addGameObject("square2", new GameObject(new Transformation(new Vector2f(450, 50), new Vector3f(0, 0, 0), new Vector2f(50f, 50f))));
//		square2.addComponent(new MeshRenderer(square2, new RectModel(), new Material(0.5f, 1, 1f)));
//		RigidBody rb2 = (RigidBody) square2.addComponent(new RigidBody(square2));
//		rb2.setVelocity(new Vector2f(-0.5f, 0f));
//		rb2.bounce = 1;
//		square2.addComponent(new BoxCollider(square2));
//		
//		
//		
//		
//		
//		
//	}
}