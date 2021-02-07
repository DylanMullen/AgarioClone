package me.dylanmullen.agar.game.ecs.components;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class PositionComponent implements Component
{

	private Vector3f position;

	public PositionComponent(Vector3f position)
	{
		this.position = position;
	}

	public void setPosition(Vector3f position)
	{
		this.position = position;
	}

	public void changePosition(Vector3f moveVector)
	{
		this.position = this.position.add(moveVector);
	}

	public Matrix4f getMatrix()
	{
		Matrix4f matrix = new Matrix4f();
		matrix.translate(position);
		return matrix;
	}

	public Vector3f getPosition()
	{
		return position;
	}

}
