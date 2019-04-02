package com.penguinz.BloxEngine.graphics.shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public abstract class Shader {

	private int programId;

	private FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
	
	public Shader(String vertexFile, String fragmentFile) {
		this.programId = GL20.glCreateProgram();
		
		int vertexId = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		int fragmentId = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		
		GL20.glAttachShader(programId, vertexId);
		GL20.glAttachShader(programId, fragmentId);
		
		bindAttributeLocations();
		
		GL20.glLinkProgram(programId);
		
		GL20.glValidateProgram(programId);
		
		loadUniformLocations();
		
		GL20.glDetachShader(programId, GL20.GL_VERTEX_SHADER);
		GL20.glDetachShader(programId, GL20.GL_FRAGMENT_SHADER);
		
		GL20.glDeleteShader(vertexId);
		GL20.glDeleteShader(fragmentId);
	}
	
	public void start() {
		GL20.glUseProgram(programId);
	}
	
	public void stop() {
		GL20.glUseProgram(0);
	}
	
	public void destroy() {
		GL20.glDeleteProgram(programId);
	}

	public abstract void bindAttributeLocations();
	public abstract void loadUniformLocations();
	
	private int loadShader(String filePath, int shaderType) {
		int shaderId = GL20.glCreateShader(shaderType);

		GL20.glShaderSource(shaderId, loadFile(filePath));
		GL20.glCompileShader(shaderId);
		
		if(GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			throw new IllegalStateException("Compilation error for shader \""+filePath+"\". Reason: " + GL20.glGetShaderInfoLog(shaderId, 1000));
		}
		
		return shaderId;
	}

	private String loadFile(String filePath) {
		StringBuilder vertexCode = new StringBuilder();
		String line = null ;
		try
		{
		    BufferedReader reader = new BufferedReader(new FileReader(filePath));
		    while((line = reader.readLine())!=null )
		    {
		    	vertexCode.append(line);
		    	vertexCode.append('\n');
		    }
		    reader.close();
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("Unable to load shader file from path \""+filePath+"\"", e);
		}
		
		return vertexCode.toString();
	}
	
	public void bindAttributeLocation(int index, String location) {
		GL20.glBindAttribLocation(programId, index, location);
	}
	
	public int getUniformLocation(String location) {
		return GL20.glGetUniformLocation(programId, location);
	}
	
	public void loadInt(int location, int value) {
		GL20.glUniform1i(location, value);
	}
	
	public void loadFloat(int location, float value) {
		GL20.glUniform1f(location, value);
	}
	
	public void loadBoolean(int location, boolean value) {
		GL20.glUniform1i(location, value ? 1 : 0);
	}
	
	public void loadVector3f(int location, Vector3f value) {
		GL20.glUniform3f(location, value.x, value.y, value.z);
	}
	
	public void loadMatrix4f(int location, Matrix4f value) {
		value.store(floatBuffer);
		floatBuffer.flip();
		GL20.glUniformMatrix4(location, false, floatBuffer);
	}
	
	public int getProgramId() {
		return programId;
	}

}
