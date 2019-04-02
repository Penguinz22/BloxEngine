package com.penguinz.BloxEngine.utils;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.penguinz.BloxEngine.data.Transformation;

public class MatrixMath {

	public static Matrix4f createModelMatrix(Transformation transformation) {
		Matrix4f matrix = new Matrix4f();
		Matrix4f.setIdentity(matrix);
		Matrix4f.translate(transformation.position, matrix, matrix);
		Matrix4f.rotate(transformation.rotation.x, new Vector3f(1, 0, 0), matrix, matrix);
		Matrix4f.rotate(transformation.rotation.y, new Vector3f(0, 1, 0), matrix, matrix);
		Matrix4f.rotate(transformation.rotation.z, new Vector3f(0, 0, 1), matrix, matrix);
		Matrix4f.scale(new Vector3f(transformation.scale.x, -transformation.scale.y, 1), matrix, matrix);
		
		return matrix;
	}
	
	public static Matrix4f createViewMatrix(Transformation transformation, float zoom) {
		Matrix4f matrix = new Matrix4f();
		Matrix4f.setIdentity(matrix);
		
		Matrix4f.rotate(transformation.rotation.x, new Vector3f(1, 0, 0), matrix, matrix);
		Matrix4f.rotate(transformation.rotation.y, new Vector3f(0, 1, 0), matrix, matrix);
		//Matrix4f.rotate(transformation.rotation.z, new Vector3f(0, 0, 1), matrix, matrix);
		Matrix4f.translate(new Vector3f(-transformation.position.x, -transformation.position.y, -zoom), matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createOrthographicMatrix(float left, float right, float top, float bottom, float near, float far) {
		Matrix4f matrix = new Matrix4f();

		matrix.m00 = 2.0f / (right - left);
		matrix.m01 = 0.0f;
		matrix.m02 = 0.0f;
		matrix.m03 = 0.0f;
	 
		matrix.m10 = 0.0f;
		matrix.m11 = 2.0f / (top - bottom);
		matrix.m12 = 0.0f;
		matrix.m13 = 0.0f;
	 
		matrix.m20 = 0.0f;
		matrix.m21 = 0.0f;
		matrix.m22 = -2.0f / (far - near);
		matrix.m23 = 0.0f;
	 
		matrix.m30 = -(right + left  ) / (right - left  );
		matrix.m31 = -(top   + bottom) / (top   - bottom);
		matrix.m32 = -(far   + near  ) / (far   - near  );
		matrix.m33 = 1.0f;
	 
		return matrix;
	}
	
	public static Matrix4f createProjectionMatrix() {
		Matrix4f projectionMatrix = new Matrix4f();
		float aspectRatio = (float) Display.getWidth()/(float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(90 /2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = 1000 - 0.1f;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((1000 + 0.1f) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * 0.1f * 1000) / frustum_length);
		projectionMatrix.m33 = 0;
		
		return projectionMatrix;
	}
	
}
