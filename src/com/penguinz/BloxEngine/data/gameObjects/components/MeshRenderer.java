package com.penguinz.BloxEngine.data.gameObjects.components;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

import com.penguinz.BloxEngine.data.gameObjects.GameObject;
import com.penguinz.BloxEngine.data.materials.Material;
import com.penguinz.BloxEngine.data.models.Model;
import com.penguinz.BloxEngine.graphics.shaders.BasicShader;
import com.penguinz.BloxEngine.utils.MatrixMath;

public class MeshRenderer extends Component {

	private Model model;
	private Material material;
	
	public MeshRenderer(GameObject parent, Model model, Material material) {
		super(parent);
		this.model = model;
		this.material = material;
	}
	
	public MeshRenderer(GameObject parent, Model model) {
		super(parent);
		this.model = model;
		this.material = new Material();
	}
	
	public void render(BasicShader shader) {
		this.model.bindVao();
		shader.loadModelMatrix(MatrixMath.createModelMatrix(getParent().getTransformation()));
		
		if(material.hasTexture()) {
			shader.loadHasTexture(true);
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, material.getTexture().getTextureId());
		}
		else shader.loadHasTexture(false);
		
		shader.loadColor(material.getColor());
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		
		this.model.unbindVao();
	}
	
	@Override
	public ComponentType getType() {
		return ComponentType.MESH_RENDERER;
	}
	
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	public Material getMaterial() {
		return material;
	}
	
}
