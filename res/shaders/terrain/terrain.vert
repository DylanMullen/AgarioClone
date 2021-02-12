#version 400 core

uniform mat4 projectionMatrix;
uniform mat4 viewMat;
uniform mat4 modelMat;

uniform vec3 chunkColour;
uniform bool insideArea;

const float darknessMultiplier = 0.75;

layout (location = 0) in vec3 position;

out vec3 pass_colour;

void main(void) {
	gl_Position = projectionMatrix * viewMat * modelMat * vec4(position, 1.0);
	
	if(insideArea)
		pass_colour = vec3(chunkColour*darknessMultiplier);
	else
		pass_colour = chunkColour;
}