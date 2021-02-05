#version 400 core

uniform mat4 projectionMatrix;
uniform mat4 viewMat;
uniform mat4 modelMat;

in vec3 position;

out vec3 colour;

void main(void) {
	gl_Position = projectionMatrix * viewMat * modelMat * vec4(position,1.0);
	colour = vec3(1,0.5,1);
}