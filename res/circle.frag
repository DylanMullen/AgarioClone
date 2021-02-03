#version 400 core

in vec3 colour;

uniform vec2 u_resolution;

out vec4 out_Color;

float draw_circle(vec2 coord, float radius) {
    return step(length(coord), radius);
}

void main(void){
	vec2 coord = gl_FragCoord.xy/u_resolution;
    float circle = draw_circle(coord, 0.3);
    vec3 color = vec3(circle);

	gl_FragColor = vec4(color, 1.0);
}