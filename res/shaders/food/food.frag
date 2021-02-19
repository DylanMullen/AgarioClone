#version 400 core

in vec2 pass_textureCoords;
in vec3 pass_colour;
in float pass_time;

mat2 rotate(float angle){
    return mat2(cos(angle),-sin(angle),
                sin(angle),cos(angle));
}

// Credit https://www.shadertoy.com/view/llVyWW
float sdPentagon( in vec2 p, in float r )
{
    const vec3 k = vec3(0.809016994,0.587785252,0.726542528); // pi/5: cos, sin, tan
    p.y = -p.y;
    p.x = abs(p.x);
    p -= 2.0*min(dot(vec2(-k.x,k.y),p),0.0)*vec2(-k.x,k.y);
    p -= 2.0*min(dot(vec2( k.x,k.y),p),0.0)*vec2( k.x,k.y);
	p -= vec2(clamp(p.x,-r*k.z,r*k.z),r);    
    return length(p)*sign(p.y);
}

void main(void) {
	vec2 pixelPosition = pass_textureCoords*vec2(1,1);
	
	pixelPosition = rotate(pass_time)*pixelPosition;
	
	float d = sdPentagon(pixelPosition-0.5, 0.40);

    vec3 col = vec3(1.0) + sign(d) * pass_colour;
    float alpha = 1.0 - smoothstep(0.0,0.015,d);
    
	col = mix(col, pass_colour, alpha);
	gl_FragColor = vec4(col, alpha);
}