package com.penguinz.BloxEngine.utils;

import org.lwjgl.util.vector.Vector2f;

public class VectorMath {

	public static Vector2f max(Vector2f vec1, Vector2f vec2) {
		Vector2f max = new Vector2f();
		
		if(vec2.x > vec1.x)
			max.x = vec2.x;
		else
			max.x = vec1.x;
		
		if(vec2.y > vec1.y)
			max.y = vec2.y;
		else
			max.y = vec1.y;
		return max;
	}
	
	
	public static float max(Vector2f vec) {
		return vec.x > vec.y ? vec.x : vec.y;
	}
	
	public static Vector2f reflect(Vector2f vel, Vector2f norm, float bounce) {
		Vector2f ref = new Vector2f();
		float dot = -2 * Vector2f.dot(vel, norm);
		norm.x *= dot;
		norm.y *= dot;
		Vector2f.add(norm, vel, ref);
		return new Vector2f(bounce*ref.x, bounce*ref.y);
	}
}
