package com.penguinz.BloxEngine.graphics;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.penguinz.BloxEngine.data.gameObjects.Camera;
import com.penguinz.BloxEngine.data.gameObjects.GameObject;
import com.penguinz.BloxEngine.data.gameObjects.Tilemap;
import com.penguinz.BloxEngine.data.gameObjects.components.Component.ComponentType;
import com.penguinz.BloxEngine.data.gameObjects.components.MeshRenderer;
import com.penguinz.BloxEngine.data.gameObjects.components.TilemapRenderer;
import com.penguinz.BloxEngine.graphics.shaders.BasicShader;

public class Renderer {
	
	private BasicShader shader;
	
	public Renderer() {
		this.shader = new BasicShader();
	}
	
	public void prepare(Camera camera) {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(camera.getBackgroundColor().x, camera.getBackgroundColor().y, camera.getBackgroundColor().z, 1);
	}
	
	public void render(Camera camera, List<GameObject> objects) {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		shader.start();
		shader.loadViewMatrix(camera.getViewMatrix());
		shader.loadOrthoMatrix(camera.getOrthographicMatrix());
		for(GameObject object: objects) {
			MeshRenderer meshRenderer = (MeshRenderer) object.getComponent(ComponentType.MESH_RENDERER);
			meshRenderer.render(shader);
		}
		shader.stop();
	}
	
	public void renderTilemaps(Camera camera, List<Tilemap> tilemaps) {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		shader.start();
		shader.loadViewMatrix(camera.getViewMatrix());
		shader.loadOrthoMatrix(camera.getOrthographicMatrix());
		for(Tilemap tilemap: tilemaps) {
			TilemapRenderer tilemapRenderer = (TilemapRenderer) tilemap.getComponent(ComponentType.TILEMAP_RENDERER);
			tilemapRenderer.render(shader);
		}
		shader.stop();
	}
	
}
