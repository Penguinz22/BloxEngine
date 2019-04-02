package com.penguinz.BloxEngine.data.gameObjects.components;

import com.penguinz.BloxEngine.data.gameObjects.GameObject;

public class Component {

	private GameObject parent;
	
	public Component(GameObject parent) {
		this.parent = parent;
	}
	
	public enum ComponentType {
		UNKOWN,
		MESH_RENDERER,
		TILEMAP_RENDERER,
		RIGID_BODY,
		BOX_COLLIDER,
		PLANE_COLLIDER;
	}

	public GameObject getParent() {
		return parent;
	}
	
	public ComponentType getType() {
		return ComponentType.UNKOWN;
	}
	
}
