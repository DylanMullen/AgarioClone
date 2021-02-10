#version 400 core

in vec3 colour;
in vec2 pass_textureCoords;

const vec2 center = vec2(0.5,0.5);

float draw_circle(vec2 coord, float radius) {
    return step(length(coord), radius);
}

void main(void) {
	vec2 pixelPosition = pass_textureCoords * vec2(1,1);
	float circle = draw_circle(vec2(pixelPosition.x-0.5,pixelPosition.y-0.5), 0.5);
	if(circle == 0)
		discard;
	else
	{
		vec3 col = colour;
		gl_FragColor = vec4(col,1);
	}
}