package com.penguinz.BloxEngine.data.physics;

import org.lwjgl.util.vector.Vector2f;

import com.penguinz.BloxEngine.utils.VectorMath;

public class AABB extends Collider {

	private Vector2f min, max;
	
	public AABB(Vector2f min, Vector2f max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public IntersectData intersecting(Plane other) {
		Vector2f boxDist = Vector2f.sub(max, min, null);
		Vector2f center = Vector2f.add(min, new Vector2f(boxDist.x/2, boxDist.y/2), null);
		
		float distFromCenter = Math.abs(Vector2f.dot(other.getNormal(), center) + other.getDistance());
		float distFromBox = distFromCenter - Vector2f.dot(other.getNormal(), new Vector2f(boxDist.x/2, boxDist.y/2));

		return new IntersectData(distFromBox < 0, new Vector2f(other.getNormal().x*distFromBox, other.getNormal().y*distFromBox));
	}
	
	@Override
	public IntersectData intersecting(AABB other) {
		Vector2f distances1 = Vector2f.sub(other.getMin(), max, null);
		Vector2f distances2 = Vector2f.sub(min, other.getMax(), null);
		Vector2f distances = VectorMath.max(distances1, distances2);
		float maxDistance = VectorMath.max(distances);
		
		return new IntersectData(maxDistance < 0, distances);
	}
	
	public Vector2f getMin() {
		return min;
	}
	
	public Vector2f getMax() {
		return max;
	}
	
}
