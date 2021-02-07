package me.dylanmullen.agar.game.collision;

import org.joml.Vector2f;

public class SquareCollision implements Collision
{

	private Vector2f topLeft;
	private Vector2f bottomRight;

	private boolean solid;

	public SquareCollision(Vector2f topLeft, Vector2f bottomRight)
	{
		this(topLeft, bottomRight, true);
	}

	public SquareCollision(Vector2f topLeft, Vector2f bottomRight, boolean solid)
	{
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
		this.solid = solid;
	}

	public boolean isColliding(Vector2f point)
	{
		return (point.x <= topLeft.x && point.x >= bottomRight.x) && (point.y <= topLeft.y && point.y >= bottomRight.y);
	}

	public boolean isColliding(SquareCollision boundingSquare)
	{
		return (boundingSquare.bottomRight.x <= topLeft.x && boundingSquare.topLeft.x >= bottomRight.x)
				&& (boundingSquare.bottomRight.y <= topLeft.y && boundingSquare.topLeft.y >= bottomRight.y);
	}

	public boolean isSolid()
	{
		return solid;
	}

	public void setSolid(boolean solid)
	{
		this.solid = solid;
	}

	public Vector2f getTopLeft()
	{
		return topLeft;
	}

	public Vector2f getBottomRight()
	{
		return bottomRight;
	}
}
