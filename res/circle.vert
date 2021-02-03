#version 400 core

in vec3 position;

out vec3 colour;

void main(void){
	gl_Position = vec4(position.xyz,1.0);
	colour = vec3(position.x+0.5,1.0,position.z+0.5);
}