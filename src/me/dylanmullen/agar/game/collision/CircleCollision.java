package me.dylanmullen.agar.game.collision;

import org.joml.Vector2f;

public class CircleCollision implements Collision
{

	private Vector2f origin;
	private float radius;
	private boolean solid;

	public CircleCollision(Vector2f origin, float radius, boolean solid)
	{
		this.origin = origin;
		this.radius = radius;
		this.solid = solid;
	}

	public boolean isColliding(Vector2f point)
	{
		float distanceToPoint = (float) Math
				.sqrt(((point.x - origin.x) * (point.x - origin.x)) + ((point.y - origin.y) * (point.y - origin.y)));
		return distanceToPoint < radius;
	}

	public boolean isColliding(CircleCollision circle)
	{
		float distanceToCircle = (float) Math
				.sqrt(((origin.x - circle.getOrigin().x) * (origin.x - circle.getOrigin().x))
						+ ((origin.x - circle.getOrigin().y) * (origin.y - circle.getOrigin().y)));
		return distanceToCircle < (circle.getRadius() + radius);
	}

	public boolean isColliding(SquareCollision square)
	{
		float x = Math.max(square.getBottomRight().x, Math.min(origin.x, square.getTopLeft().x));
		float y = Math.max(square.getBottomRight().y, Math.min(origin.y, square.getTopLeft().y));
		float distance = (float) Math.sqrt(((x - origin.x) * (x - origin.x)) + ((y - origin.y) * (y - origin.y)));
		return distance < radius;
	}

	public Vector2f getOrigin()
	{
		return origin;
	}

	public boolean isSolid()
	{
		return solid;
	}

	public void setSolid(boolean solid)
	{
		this.solid = solid;
	}

	public void setRadius(float radius)
	{
		this.radius = radius;
	}

	public float getRadius()
	{
		return radius;
	}

}
