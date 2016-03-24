#version 150 core

uniform sampler2D texture_diffuse;
uniform vec3 Color;
in vec4 pass_Color;
in vec2 pass_TextureCoord;

out vec4 out_Color;

void main(void) {
	out_Color = pass_Color;
	// Override out_Color with our texture pixel
	out_Color = texture(texture_diffuse, pass_TextureCoord)*vec4(Color,1);
	//out_Color = vec4(vec3(Color.rgb),1);
}