package com.penguinz.BloxEngine.data.physics;

import org.lwjgl.util.vector.Vector2f;

public class IntersectData {

	private boolean doesIntersect;
	private Vector2f direction;
	
	public IntersectData(boolean doesIntersect, Vector2f direction) {
		this.doesIntersect = doesIntersect;
		this.direction = direction;
	}
	
	public boolean getDoesIntersect() {
		return doesIntersect;
	}
	
	public Vector2f getDirection() {
		return direction;
	}
	
	public float getDistance() {
		return direction.length();
	}
	
}
