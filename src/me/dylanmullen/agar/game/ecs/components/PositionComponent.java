package me.dylanmullen.agar.game.ecs.components;

import org.joml.Vector2f;

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

	public Vector2f getPosition()
	{
		return position;
	}

}
