#version 400 core

uniform mat4 projectionMatrix;
uniform mat4 viewMat;
uniform mat4 modelMat;
uniform vec3 chunkColour;
uniform bool outOfBounds;

layout (location = 0) in vec3 position;

out vec3 colour;

void main(void) {
	gl_Position = projectionMatrix * viewMat * modelMat * vec4(position, 1.0);
	
	if(outOfBounds)
	{
		colour = vec3(chunkColour*0.75);
	}
	else
	{
		colour = chunkColour;
	}
}