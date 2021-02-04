package me.dylanmullen.agar.game.ecs.components;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class PositionComponent extends Component
{

	private Vector2f position;

	public PositionComponent(Vector2f position)
	{
		this.position = position;
	}

	public void setPosition(Vector2f position)
	{
		this.position = position;
	}

	public void increasePosition(Vector2f moveVector)
	{
		this.position = this.position.add(moveVector);
	}

	public void decreasePosition(Vector2f moveVector)
	{
		this.position = this.position.sub(moveVector);
	}

	public Matrix4f getMatrix()
	{
		Matrix4f matrix = new Matrix4f();
		matrix.translate(new Vector3f(position, 0));
		return matrix;
	}

	public Vector2f getPosition()
	{
		return position;
	}

}
