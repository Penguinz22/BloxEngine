package com.penguinz.BloxEngine.data.models;

public class RectModel extends Model {

	private static final float[] vertices = new float[] {
			0f, 0f,
			1f, 0f,
			0f, -1f,
			1f, 0f,
			0f, -1f,
			1f, -1f
	};
	
	private static final float[] textureCoords = new float[] {
			0, 0,
			1, 0,
			0, 1,
			1, 0,
			0, 1,
			1, 1
	};
	
	public RectModel() {
		super(vertices, textureCoords);
	}

}
