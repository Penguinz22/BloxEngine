package com.penguinz.BloxEngine.data.gameObjects.components;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.penguinz.BloxEngine.data.Transformation;
import com.penguinz.BloxEngine.data.gameObjects.GameObject;
import com.penguinz.BloxEngine.data.gameObjects.Tilemap;
import com.penguinz.BloxEngine.data.models.Model;
import com.penguinz.BloxEngine.data.models.RectModel;
import com.penguinz.BloxEngine.graphics.shaders.BasicShader;
import com.penguinz.BloxEngine.utils.MatrixMath;

public class TilemapRenderer extends Component{

	private Model model;
	private Tilemap tilemap;
	
	public TilemapRenderer(GameObject parent) {
		super(parent);
		if(!(parent instanceof Tilemap)) {
			System.err.println("Added TilemapRenderer to a non Tilemap object!");
		}
		this.tilemap = (Tilemap) parent;
		this.model = new RectModel();
	}
	
	public void render(BasicShader shader) {
		this.model.bindVao();
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		shader.loadColor(new Vector3f(1,1,1));
		for(Vector2f position: tilemap.getMap().keySet()) {
			shader.loadModelMatrix(MatrixMath.createModelMatrix(new Transformation(new Vector2f(position.x*tilemap.getTileSize(), position.y*tilemap.getTileSize()),
					new Vector3f(0, 0, 0), new Vector2f(tilemap.getTileSize(), tilemap.getTileSize()))));
			shader.loadHasTexture(true);
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, tilemap.getTextures().get(tilemap.getMap().get(position)).getTextureId());
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
		}
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		this.model.unbindVao();
	}
	
	@Override
	public ComponentType getType() {
		return Component.ComponentType.TILEMAP_RENDERER;
	}
	
}
