package com.penguinz.BloxEngine.physics;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import com.penguinz.BloxEngine.data.gameObjects.components.BoxCollider;
import com.penguinz.BloxEngine.data.gameObjects.components.Component.ComponentType;
import com.penguinz.BloxEngine.data.gameObjects.components.PlaneCollider;
import com.penguinz.BloxEngine.data.gameObjects.components.RigidBody;
import com.penguinz.BloxEngine.data.physics.AABB;
import com.penguinz.BloxEngine.data.physics.Collider;
import com.penguinz.BloxEngine.data.physics.IntersectData;
import com.penguinz.BloxEngine.utils.VectorMath;

public class PhysicsEngine {

	private List<RigidBody> objects;
	
	public void update(List<RigidBody> objects, float delta) {
		this.objects = objects;
		simulate(delta);
		handleCollisions();
	}
	
	public void simulate(float delta) {
		for(RigidBody rigidBody: objects) {
			rigidBody.integrate(delta);
		}
		
	}
	
	public void handleCollisions() {
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i+1; j < objects.size(); j++) {
				Collider collider1 = null;
				Collider collider2 = null;
				
				if(objects.get(i).getParent().hasComponent(ComponentType.BOX_COLLIDER))
					collider1 = ((BoxCollider) objects.get(i).getParent().getComponent(ComponentType.BOX_COLLIDER)).getBoundingBox();
				else if(objects.get(i).getParent().hasComponent(ComponentType.PLANE_COLLIDER))
					collider1 = ((PlaneCollider) objects.get(i).getParent().getComponent(ComponentType.PLANE_COLLIDER)).getPlaneCollider();
				
				if(objects.get(j).getParent().hasComponent(ComponentType.BOX_COLLIDER))
					collider2 = ((BoxCollider) objects.get(j).getParent().getComponent(ComponentType.BOX_COLLIDER)).getBoundingBox();
				else if(objects.get(j).getParent().hasComponent(ComponentType.PLANE_COLLIDER))
					collider2 = ((PlaneCollider) objects.get(j).getParent().getComponent(ComponentType.PLANE_COLLIDER)).getPlaneCollider();
				
				IntersectData intersectData = collider1.intersecting(collider2);
				if(intersectData.getDoesIntersect()) {
					System.out.println(intersectData.getDirection());
					Vector2f direction = intersectData.getDirection().normalise(null);
					//System.out.println("before: "+objects.get(i).getVelocity());
					
					if(collider1 instanceof AABB) {
						if(objects.get(i).bounce <= 0) {
							Vector2f pos = Vector2f.sub(objects.get(i).getPosition(), new Vector2f(intersectData.getDirection().x*intersectData.getDistance(), intersectData.getDirection().y*intersectData.getDistance()), null);
							objects.get(i).getParent().getTransformation().position.x = pos.x;
							objects.get(i).getParent().getTransformation().position.y = pos.y;
							objects.get(i).getVelocity().x = 0;
						} else
							objects.get(i).setVelocity(VectorMath.reflect(objects.get(i).getVelocity(), direction, objects.get(i).bounce*10000));
						
					}
					
					//System.out.println("after:" +objects.get(i).getVelocity());
					
					if(collider2 instanceof AABB) {
						if(objects.get(j).bounce <= 0) {
							Vector2f pos = Vector2f.add(objects.get(j).getPosition(), intersectData.getDirection(), null);
							objects.get(j).getParent().getTransformation().position.x = pos.x;
							objects.get(j).getParent().getTransformation().position.y = pos.y;
							objects.get(j).getVelocity().x = 0;
						} else
							objects.get(j).setVelocity(VectorMath.reflect(objects.get(j).getVelocity(), direction, objects.get(j).bounce*10000));
					}
				
				}
			}
		}
	}
	
}
