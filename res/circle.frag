#version 400 core

in vec3 colour;

out vec4 out_Color;

void main(void) {
	gl_FragColor = vec4(colour, 1.0);
}