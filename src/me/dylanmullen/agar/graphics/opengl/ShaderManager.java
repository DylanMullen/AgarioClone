package me.dylanmullen.agar.graphics.opengl;

import java.util.ArrayList;
import java.util.List;

public class ShaderManager
{

	private List<Shader> currentShaders;

	public ShaderManager()
	{
		this.currentShaders = new ArrayList<Shader>();
	}

	public Shader createShader(String name, String vertPath, String fragPath)
	{
		Shader shader = getShader(name);
		if (shader != null)
			return shader;

		shader = new Shader(name, vertPath, fragPath);
		currentShaders.add(shader);
		return shader;
	}

	public Shader getShader(String name)
	{
		for (Shader shader : currentShaders)
			if (shader.getName().equalsIgnoreCase(name))
				return shader;
		return null;
	}

}
