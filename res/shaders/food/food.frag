#version 400 core

in vec2 pass_textureCoords;
in vec3 pass_colour;

void main(void) {
	vec2 pixelPosition = pass_textureCoords*vec2(1,1);
	
	float dist = distance(pixelPosition, vec2(0.5,0.5));
	float delta = fwidth(dist);
	float alpha = 1.0-smoothstep(0.5-delta,0.5,dist);
	
	gl_FragColor = vec4(pass_colour, alpha);
}