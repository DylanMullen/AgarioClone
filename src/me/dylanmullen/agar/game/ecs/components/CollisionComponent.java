package me.dylanmullen.agar.game.ecs.components;

import me.dylanmullen.agar.game.collision.Collision;

public class CollisionComponent implements Component
{

	private PositionComponent positionComponent;
	private Collision collision;

	public CollisionComponent(PositionComponent position, Collision collisionArea)
	{
		this.collision = collisionArea;
		this.positionComponent = position;
	}

	public Collision getCollision()
	{
		return collision;
	}
	
	public PositionComponent getPositionComponent()
	{
		return positionComponent;
	}

}
