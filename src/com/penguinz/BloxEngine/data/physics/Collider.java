package com.penguinz.BloxEngine.data.physics;

public abstract class Collider {
	
	public IntersectData intersecting(Collider col) {
		if(col instanceof AABB)
			return intersecting((AABB) col);
		else if(col instanceof Plane)
			return intersecting((Plane) col);
		return null;
	}
	
	public abstract IntersectData intersecting(AABB other);
	
	public abstract IntersectData intersecting(Plane other);
	
}
