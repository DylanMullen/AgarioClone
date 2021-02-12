package me.dylanmullen.agar.graphics.opengl;

public class Model
{

	private VAO modelData;
	private float scale;

	public Model(VAO modelData, float scale)
	{
		this.modelData = modelData;
		this.scale = scale;
	}

	public VAO getModelData()
	{
		return modelData;
	}

	public float getScale()
	{
		return scale;
	}

	public void incrementScale(float scale)
	{
		this.scale += scale;
	}

	public void setScale(float scale)
	{
		this.scale = scale;
	}
}
