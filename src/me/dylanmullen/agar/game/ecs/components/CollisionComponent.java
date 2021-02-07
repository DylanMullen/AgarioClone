package me.dylanmullen.agar.game.ecs.components;

import org.joml.Vector2f;

import me.dylanmullen.agar.game.collision.CircleCollision;
import me.dylanmullen.agar.game.collision.Collision;
import me.dylanmullen.agar.game.collision.SquareCollision;

public class CollisionComponent implements Component
{

	private PositionComponent position;
	private Collision collision;

	public CollisionComponent(PositionComponent position, Collision collisionArea)
	{
		this.position = position;
		this.collision = collisionArea;
	}

	public Collision getCollision()
	{
		return collision;
	}

	public PositionComponent getPosition()
	{
		return position;
	}

	public void updatePosition(Vector2f movementVector)
	{
		if (getCollision() instanceof SquareCollision)
		{
			SquareCollision square = (SquareCollision) getCollision();
			square.updatePositions(movementVector);
			return;
		}
		CircleCollision circle = (CircleCollision)getCollision();
		
	}

}
