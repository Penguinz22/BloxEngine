package com.penguinz.BloxEngine.data.models;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Model {

	private int vaoId;
	private int vertexCount;
	
	private List<Integer> buffers = new ArrayList<Integer>();

	public Model(float[] vertices, float[] textureCoords) {
		addData(vertices, textureCoords);
	}
	
	public void addData(float[] vertices, float[] textureCoords) {
		if(vaoId != 0)
			deleteData();
		
		this.vaoId = GL30.glGenVertexArrays();
		this.vertexCount = vertices.length/2;
		bindVao();
		
		addVbo(0, 2, vertices);
		addVbo(1, 2, textureCoords);
		
		unbindVao();
	}
	
	public void addVbo(int index, int dimensions, float[] data) {
		int vboId = GL15.glGenBuffers();
		buffers.add(vboId);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		FloatBuffer buffer = createFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, dimensions, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	private FloatBuffer createFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public void bindVao() {
		GL30.glBindVertexArray(vaoId);
	}
	
	public void unbindVao() {
		GL30.glBindVertexArray(0);
	}
	
	public void deleteData() {
		GL30.glDeleteVertexArrays(vaoId);
		for(int buffer: buffers)
			GL15.glDeleteBuffers(buffer);
		
		vaoId = 0;
		vertexCount = 0;
		buffers.clear();
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	
}
