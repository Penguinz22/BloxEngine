package com.penguinz.BloxEngine.data;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class Transformation {

	public Vector3f rotation;
	public Vector2f position, scale;
	
	public Transformation() {
		this.position = new Vector2f(0, 0);
		this.rotation = new Vector3f(0, 0, 0);
		this.scale = new Vector2f(1, 1);
	}
	
	public Transformation(Vector2f position, Vector3f rotation, Vector2f scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public static Transformation add(Transformation a, Transformation b) {
		return new Transformation(Vector2f.add(a.position,  b.position, null),  Vector3f.add(a.rotation,  b.rotation, null), a.scale);
	}
	
	public static Transformation reverse(Transformation a) {
		return new Transformation(new Vector2f(a.position.x * -1, a.position.y * -1), a.rotation.negate(null), a.scale);
	}
	
}
