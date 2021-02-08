package me.dylanmullen.agar.game.ecs.components;

import java.util.UUID;

import org.joml.Vector2f;

import me.dylanmullen.agar.game.collision.CircleCollision;
import me.dylanmullen.agar.game.collision.Collision;
import me.dylanmullen.agar.game.collision.SquareCollision;

public class CollisionComponent implements Component
{

	private UUID entityUUID;
	private PositionComponent position;
	private Collision collision;

	public CollisionComponent(UUID entityUUID, PositionComponent position, Collision collisionArea)
	{
		this.entityUUID = entityUUID;
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
		getCollision().updatePositions(movementVector);
	}
	
	public UUID getEntityUUID()
	{
		return entityUUID;
	}

}
