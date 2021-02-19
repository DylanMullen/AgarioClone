package me.dylanmullen.agar.game.ecs.components;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class PositionComponent implements Component
{

	private Vector3f position;
	private Vector2f movementVector;

	private boolean moved;

	public PositionComponent(Vector3f position)
	{
		this.position = position;
		this.movementVector = new Vector2f();
	}

	@Override
	public void load()
	{
		moved = false;
	}

	@Override
	public void unload()
	{
		position = null;
		movementVector = null;
		moved = false;
	}

	public void setPosition(Vector3f position)
	{
		this.position = position;
		this.moved = true;
	}

	public void changePosition(Vector3f moveVector)
	{
		makeLegal(moveVector);
		this.position = this.position.add(moveVector.x, moveVector.y, moveVector.z);
		this.movementVector = new Vector2f(moveVector.x, moveVector.z);
		this.moved = true;
	}

	private void makeLegal(Vector3f vector)
	{
		if (position.x + vector.x >= 8 || position.x + vector.x <= -8)
			vector.set(0, vector.y, vector.z);
		if (position.z + vector.z >= 8 || position.z + vector.z <= -8)
			vector.set(vector.x, vector.y, 0);
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

	public Vector2f getMovementVector()
	{
		return movementVector;
	}

	public boolean hasMoved()
	{
		return moved;
	}

	public void setMoved(boolean moved)
	{
		this.moved = moved;
	}

}
