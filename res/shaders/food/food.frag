#version 400 core

in vec2 pass_textureCoords;
in vec3 pass_colour;

bool inCircle(vec2 coord) {
    return step(length(coord), 0.5)==1;
}

void main(void) {
	vec2 pixelPosition = pass_textureCoords*vec2(1,1);
	if(!inCircle(vec2(pixelPosition.x-0.5,pixelPosition.y-0.5)))
		discard;
	else
		gl_FragColor = vec4(pass_colour,1);
}