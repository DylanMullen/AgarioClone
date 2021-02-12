#version 400 core

in vec3 pass_colour;

void main(void) {
	gl_FragColor = vec4(pass_colour, 1.0);
}	