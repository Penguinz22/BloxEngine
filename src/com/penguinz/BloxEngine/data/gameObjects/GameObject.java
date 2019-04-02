package com.penguinz.BloxEngine.data.gameObjects;

import java.util.ArrayList;
import java.util.List;

import com.penguinz.BloxEngine.data.Transformation;
import com.penguinz.BloxEngine.data.gameObjects.components.Component;
import com.penguinz.BloxEngine.data.gameObjects.components.Component.ComponentType;

public class GameObject {
	
	private GameObject parent;
	
	private Transformation transformation;
	private Transformation originTransformation;
	
	private List<Component> components = new ArrayList<Component>();
	
	public GameObject() {
		this.transformation = new Transformation();
		this.originTransformation = Transformation.reverse(transformation);
	}
	
	public GameObject(Transformation transformation) {
		this.transformation = transformation;
		this.originTransformation = Transformation.reverse(transformation);
	}
	
	public GameObject(GameObject parent) {
		this.parent = parent;
		this.transformation = new Transformation();
		this.originTransformation = Transformation.reverse(transformation);
	}
	
	public GameObject(GameObject parent, Transformation transformation) {
		this.parent = parent;
		this.transformation = transformation;
		this.originTransformation = Transformation.reverse(transformation);
	}
	
	public Component addComponent(Component component) {
		components.add(component);
		return component;
	}
	
	public boolean hasComponent(ComponentType type) {
		return getComponent(type) != null;
	}
	
	public Component getComponent(ComponentType type) {
		for(Component component:  this.components) {
			if(component.getType() == type)
				return component;
		}
		return null;
	}
	
	public void setParent(GameObject parent) {
		this.parent = parent;
	}
	
	public GameObject getParent() {
		return parent;
	}
	
	public boolean hasParent() {
		return parent != null;
	}
	
	public Transformation getTransformation() {
		if(hasParent()) {
			return Transformation.add(transformation, Transformation.add(parent.getOriginTransform(), parent.getTransformation()));
		}
		return transformation;
	}
	
	public Transformation getOriginTransform() {
		return originTransformation;
	}
	
}
