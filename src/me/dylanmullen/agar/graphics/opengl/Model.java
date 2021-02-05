package me.dylanmullen.agar.graphics.opengl;

import org.joml.Matrix4f;

public class Model
{

	private VAO modelData;
	private float scale;

	public Model(VAO modelData, float scale)
	{
		this.modelData = modelData;
		this.scale=scale;
	}

	public VAO getModelData()
	{
		return modelData;
	}
	
	public float getScale()
	{
		return scale;
	}
}
