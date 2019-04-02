package com.penguinz.BloxEngine.data.materials;

import org.lwjgl.util.vector.Vector3f;

public class Material {

	private Vector3f color;
	private Texture texture;
	
	public Material() {
		this.color = new Vector3f(0.75f, 0.75f, 0.75f);
	}
	
	public Material(float r, float g, float b) {
		this.color = new Vector3f(r, g, b);
	}
	
	public Material(Texture texture) {
		this.color = new Vector3f(1f, 1f, 1f);
		this.texture = texture;
	}
	
	public boolean hasTexture() {
		return texture != null;
	}
	
	public Vector3f getColor() {
		return color;
	}
	
	public Texture getTexture() {
		return texture;
	}
	
}
