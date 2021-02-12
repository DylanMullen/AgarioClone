#version 400 core

in vec3 colour;
in vec2 pass_textureCoords;

const vec2 center = vec2(0.5,0.5);

float draw_circle(vec2 coord, float radius) {
    return step(length(coord), radius);
}

void main(void) {
	gl_FragColor = vec4(colour,1);

}