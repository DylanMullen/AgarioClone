package me.dylanmullen.agar.game.events.api.event.collision;

import java.util.UUID;

import org.joml.Vector2f;

import me.dylanmullen.agar.game.events.api.Event;

public class BoundsCollideEvent implements Event
{

	private UUID entity;
	private Vector2f bounds;

	public BoundsCollideEvent(UUID entity, Vector2f bounds)
	{
		this.entity = entity;
		this.bounds = bounds;
	}
	
	public Vector2f getBounds()
	{
		return bounds;
	}

	public UUID getEntity()
	{
		return entity;
	}
}
