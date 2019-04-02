package com.penguinz.BloxEngine.data.gameObjects;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.penguinz.BloxEngine.data.Transformation;
import com.penguinz.BloxEngine.data.gameObjects.components.Component;
import com.penguinz.BloxEngine.data.gameObjects.components.Component.ComponentType;
import com.penguinz.BloxEngine.utils.MatrixMath;

public class Camera extends GameObject {

	private Vector3f backgroundColor;
	
	private Matrix4f viewMatrix;
	private Matrix4f orthoMatrix;
	
	public float zoom;
	
	public Camera(float zoom) {
		this.backgroundColor = new Vector3f(0.5f, 0.5f, 0.5f);
		this.zoom = zoom;
		update();
	}
	
	public Camera(float zoom, Transformation transformation) {
		super(transformation);
		this.backgroundColor = new Vector3f(0.5f, 0.5f, 0.5f);
		this.zoom = zoom;
		update();
	}
	
	public Camera(float zoom, Vector3f backgroundColor) {
		this.backgroundColor = backgroundColor;
		this.zoom = zoom;
		update();
	}
	
	public Camera(float zoom, Transformation transformation, Vector3f backgroundColor) {
		super(transformation);
		this.backgroundColor = backgroundColor;
		this.zoom = zoom;
		update();
	}
	
	public void update() {
		this.viewMatrix = MatrixMath.createViewMatrix(getTransformation(), zoom);
		this.orthoMatrix = MatrixMath.createOrthographicMatrix(0, 1280, 0, 720, -1, 1);
	}
	
	@Override
	public Component addComponent(Component component) {
		if(component.getType() == ComponentType.MESH_RENDERER) {
			System.err.println("You cannot add a Mesh Renderer Component to a Camera!");
			return null;
		}
		return super.addComponent(component);
	}
	
	public Matrix4f getViewMatrix() {
		return viewMatrix;
	}
	
	public Matrix4f getOrthographicMatrix() {
		return orthoMatrix;
	}
	
	public void setBackgroundColor(Vector3f backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public Vector3f getBackgroundColor() {
		return backgroundColor;
	}
	
}
