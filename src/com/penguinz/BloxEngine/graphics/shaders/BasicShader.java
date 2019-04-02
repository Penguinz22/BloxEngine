package com.penguinz.BloxEngine.graphics.shaders;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class BasicShader extends Shader {
	
	private int uniformModelMatrix;
	private int uniformViewMatrix;
	private int uniformOrthoMatrix;
	
	private int uniformHasTexture;
	private int uniformColor;
	
	public BasicShader() {
		super("shaders/basicVertexShader.glsl", "shaders/basicFragmentShader.glsl");
	}

	@Override
	public void bindAttributeLocations() {
		super.bindAttributeLocation(0, "vertices");
		super.bindAttributeLocation(1, "textureCoords");
	}

	@Override
	public void loadUniformLocations() {
		uniformModelMatrix = super.getUniformLocation("modelMatrix");
		uniformViewMatrix = super.getUniformLocation("viewMatrix");
		uniformOrthoMatrix = super.getUniformLocation("orthoMatrix");
		
		uniformHasTexture = super.getUniformLocation("hasTexture");
		uniformColor = super.getUniformLocation("color");
	}
	
	public void loadModelMatrix(Matrix4f modelMatrix) {
		super.loadMatrix4f(uniformModelMatrix, modelMatrix);
	}
	
	public void loadViewMatrix(Matrix4f viewMatrix) {
		super.loadMatrix4f(uniformViewMatrix, viewMatrix);
	}
	
	public void loadOrthoMatrix(Matrix4f orthoMatrix) {
		super.loadMatrix4f(uniformOrthoMatrix, orthoMatrix);
	}
	
	public void loadHasTexture(boolean hasTexture) {
		super.loadBoolean(uniformHasTexture, hasTexture);
	}
	
	public void loadColor(Vector3f color) {
		super.loadVector3f(uniformColor, color);
	}
	
}
