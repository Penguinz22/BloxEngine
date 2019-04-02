package com.penguinz.BloxEngine.data.gameObjects.components;

import org.lwjgl.util.vector.Vector2f;

import com.penguinz.BloxEngine.data.gameObjects.GameObject;
import com.penguinz.BloxEngine.data.physics.AABB;

public class BoxCollider extends Component {

	private AABB aabb;
	
	public BoxCollider(GameObject parent) {
		super(parent);
		update();
	}

	public void update() {
		this.aabb = new AABB(new Vector2f(getParent().getTransformation().position.x, getParent().getTransformation().position.y),
				new Vector2f(getParent().getTransformation().position.x+getParent().getTransformation().scale.x, getParent().getTransformation().position.y+getParent().getTransformation().scale.y));
	}
	
	public AABB getBoundingBox() {
		update();
		return aabb;
	}
	
	@Override
	public ComponentType getType() {
		return ComponentType.BOX_COLLIDER;
	}
	
}
