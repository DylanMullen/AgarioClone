#version 400 core

in vec3 pass_colour;
in vec2 pass_textureCoords;

bool inCircle(vec2 coord) {
    return step(length(coord), 0.5)==1;
}

void main(void) {
	vec2 pixelPos = pass_textureCoords * vec2(1,1);
	if(!inCircle(vec2(pixelPos.x-0.5,pixelPos.y-0.5)))
		discard;
	gl_FragColor = vec4(pass_colour,1);

}