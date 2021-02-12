#version 400 core

uniform mat4 projectionMatrix;
uniform mat4 viewMat;
uniform mat4 modelMat;
uniform vec3 playerColour;

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textureCoords;

out vec3 pass_colour;
out vec2 pass_textureCoords;

void main(void) {
	gl_Position = projectionMatrix * viewMat * modelMat * vec4(position,1.0);
	pass_colour = playerColour;
	pass_textureCoords = textureCoords;
}