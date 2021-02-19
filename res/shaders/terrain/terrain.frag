#version 400 core

in vec3 pass_colour;
in vec2 pass_textureCoords;

vec2 tileSquares(vec2 pixelPosition, float squares)
{
	pixelPosition *= squares;
	pixelPosition.x += step(1.0, mod(pixelPosition.y, 1.0));
	return fract(pixelPosition);
}

float box(vec2 pixelPosition, vec2 size)
{
	size = vec2(0.5,0.5)-size*0.51;
    vec2 uv = smoothstep(size,size+vec2(1e-4),pixelPosition);
    uv *= smoothstep(size,size+vec2(1e-4),vec2(1.0)-pixelPosition);
    return uv.x*uv.y;
}

void main(void) {
	vec2 pixelPosition = pass_textureCoords * vec2(1,1);
	pixelPosition = tileSquares(pixelPosition,16.0);
	float boxColour = box(pixelPosition,vec2(0.95,0.95));
	vec3 colour = mix(pass_colour,vec3(boxColour),0.75);
	gl_FragColor = vec4(colour, 1.0);
}	