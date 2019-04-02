#version 400 core

in vec2 vertices;
in vec2 textureCoords;

out vec2 passTextureCoords;

uniform mat4 modelMatrix;
uniform mat4 viewMatrix;
uniform mat4 orthoMatrix;

void main() {

	gl_Position = orthoMatrix * viewMatrix * modelMatrix * vec4(vertices, 0, 1);
	//gl_Position = orthoMatrix * modelMatrix * vec4(vertices, 0, 1);
	passTextureCoords = textureCoords;

}