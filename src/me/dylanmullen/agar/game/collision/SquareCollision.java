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

	@Override
	public boolean collide(Object target)
	{
		if (target instanceof Vector2f)
			return isColliding((Vector2f) target);
		if (target instanceof SquareCollision)
			return isColliding((SquareCollision) target);
		if (target instanceof CircleCollision)
			return isColliding((CircleCollision) target);
		return false;
	}

	public boolean isColliding(Vector2f point)
	{
		return (point.x <= bottomRight.x && point.x >= topLeft.x) && (point.y >= bottomRight.y && point.y <= topLeft.y);
	}

	public boolean isColliding(SquareCollision boundingSquare)
	{
		boolean x = ((getTopLeft().x <= boundingSquare.getBottomRight().x)
				&& (getBottomRight().x >= boundingSquare.getTopLeft().x));
		boolean y = ((getTopLeft().y <= boundingSquare.getBottomRight().y)
				&& getBottomRight().y >= boundingSquare.getTopLeft().y);
		return x && y;
	}

	public boolean isColliding(CircleCollision circle)
	{
		float x = Math.max(getBottomRight().x, Math.min(circle.getOrigin().x, getTopLeft().x));
		float y = Math.max(getBottomRight().y, Math.min(circle.getOrigin().y, getTopLeft().y));
		float distance = (float) Math.sqrt(((x - circle.getOrigin().x) * (x - circle.getOrigin().x))
				+ ((y - circle.getOrigin().y) * (y - circle.getOrigin().y)));
		return distance < circle.getRadius();
	}

	public void updatePositions(Vector2f movementVector)
	{
		this.topLeft.set(topLeft.x + movementVector.x, topLeft.y + movementVector.y);
		this.bottomRight.set(bottomRight.x + movementVector.x, bottomRight.y + movementVector.y);
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
