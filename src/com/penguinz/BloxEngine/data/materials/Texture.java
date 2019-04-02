package com.penguinz.BloxEngine.data.materials;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.TextureLoader;

public class Texture {

	private int id;
	
	public Texture(String path, String format) {
		loadFromFile(path, format);
	}
	
	private void loadFromFile(String path, String format) {
		org.newdawn.slick.opengl.Texture tex = null;
				
		try {
			tex = TextureLoader.getTexture(format, new FileInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ByteBuffer buf = ByteBuffer.allocateDirect(tex.getTextureData().length);
		buf.put(tex.getTextureData());
		buf.flip();
		this.id = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, (int) tex.getWidth(), (int)tex.getHeight(),
                 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buf);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	public int getTextureId() {
		return id;
	}
	
}
