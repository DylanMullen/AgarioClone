#version 400 core
#define PI 3.14159265359

uniform mat4 projectionMatrix;
uniform mat4 viewMat;
uniform mat4 modelMat;
uniform vec3 entityPosition;

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textureCoords;

out vec2 pass_textureCoords;
out vec3 pass_colour;

float getRandomNumber(vec2 pixelPosition)
{
	return fract(sin(dot(pixelPosition.xy,vec2(1,100)))*4000.5824);
}

vec3 getRandomColour(vec3 position)
{
	return vec3(getRandomNumber(position.xy*PI),getRandomNumber(position.yz*PI),getRandomNumber(position.xz*PI));
}

void main(void) {
	pass_textureCoords = textureCoords;
	pass_colour = getRandomColour(entityPosition);
	gl_Position = projectionMatrix * viewMat * modelMat * vec4(position,1.0);
}