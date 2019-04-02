package com.penguinz.BloxEngine.data.physics;

import org.lwjgl.util.vector.Vector2f;

public class Plane extends Collider {

	private Vector2f normal;
	private float distance;
	
	public Plane(Vector2f normal, float distance) {
		this.normal = normal;
		this.distance = distance;
	}
	
	public Plane normalized() {
		float magnitude = normal.length();
		
		return new Plane(new Vector2f(normal.x/magnitude, normal.y/magnitude), distance/magnitude);
	}

	@Override
	public IntersectData intersecting(AABB other) {
		Vector2f boxDist = Vector2f.sub(other.getMax(), other.getMin(), null);
		Vector2f center = new Vector2f(boxDist.x/2, boxDist.y/2);
		
		float distFromCenter = Math.abs(Vector2f.dot(normal, center) + distance);
		float distFromBox = distFromCenter - Vector2f.dot(normal, boxDist);
		System.out.println(center);
		return new IntersectData(distFromBox < 0, new Vector2f(normal.x*distFromBox, normal.y*distFromBox));
	}

	@Override
	public IntersectData intersecting(Plane other) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Vector2f getNormal() {
		return normal;
	}
	
	public float getDistance() {
		return distance;
	}
	
}
