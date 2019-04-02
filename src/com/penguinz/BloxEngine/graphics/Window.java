package com.penguinz.BloxEngine.graphics;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class Window {

	public static void createWindow(int width, int height) {
		ContextAttribs attribs = new ContextAttribs(3, 2)
				.withForwardCompatible(true)
				.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create(new PixelFormat(), attribs);
		} catch(LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, width, height);
	}
	
	public static boolean isClosed() {
		return Display.isCloseRequested();
	}
	
	public static void updateWindow(int fps) {
		Display.sync(fps);
		Display.update();
	}
	
	public static void destroyWindow() {
		Display.destroy();
	}
	
}
