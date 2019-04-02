package com.penguinz.BloxEngine.data.gameObjects.components;

import org.lwjgl.util.vector.Vector2f;

import com.penguinz.BloxEngine.data.gameObjects.GameObject;
import com.penguinz.BloxEngine.data.physics.Plane;

public class PlaneCollider extends Component {

	private Plane plane;
	
	private Vector2f normal;
	
	public PlaneCollider(GameObject parent, Vector2f normal) {
		super(parent);
		this.normal = normal;
		update();
	}

	public void update() {
		this.plane = new Plane(normal, -500);
	}
	
	public Plane getPlaneCollider() {
		update();
		return plane;
	}
	
	@Override
	public ComponentType getType() {
		return ComponentType.PLANE_COLLIDER;
	}
	
}
