#version 400 core

in vec2 passTextureCoords;

out vec4 gl_FragColor;

uniform sampler2D texture;
uniform int hasTexture;
uniform vec3 color;

void main() {

	vec4 fragColor = vec4(color, 1);
	
	if(hasTexture == 1) {
		fragColor = fragColor * texture2D(texture, passTextureCoords);
	}

	gl_FragColor = fragColor;

}