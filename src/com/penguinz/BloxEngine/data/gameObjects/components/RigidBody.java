package com.penguinz.BloxEngine.data.gameObjects.components;

import org.lwjgl.util.vector.Vector2f;

import com.penguinz.BloxEngine.Constants;
import com.penguinz.BloxEngine.data.gameObjects.GameObject;

public class RigidBody extends Component {

	private Vector2f position, velocity;
	
	public boolean hasGravity;
	public float bounce;
	
	public RigidBody(GameObject parent) {
		super(parent);
		this.position = parent.getTransformation().position;
		this.velocity = new Vector2f();
		this.hasGravity = true;
		bounce = 0.5f;
	}
	
	public void integrate(float delta) {
		if(hasGravity)
			Vector2f.add(velocity, Constants.GRAVITY, velocity);
		Vector2f.add(position, velocity, position);
	}
	
	public Vector2f getVelocity() {
		return velocity;
	}
	
	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}
	
	public Vector2f getPosition() {
		return position;
	}
	
	@Override
	public ComponentType getType() {
		return ComponentType.RIGID_BODY;
	}

}
