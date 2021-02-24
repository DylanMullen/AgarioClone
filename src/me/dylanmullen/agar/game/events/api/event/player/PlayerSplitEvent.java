package me.dylanmullen.agar.game.events.api.event.player;

import java.util.UUID;

import org.joml.Vector2f;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.events.api.Event;

public class PlayerSplitEvent implements Event
{

	private Entity entity;
	private Vector2f direction;

	public PlayerSplitEvent(UUID entity, Vector2f direction)
	{
		this.entity = GameController.getInstance().getEntityHandler().getEntity(entity);
		this.direction = direction;
	}

	public Entity getEntity()
	{
		return entity;
	}

	public Vector2f getDirection()
	{
		return direction;
	}
}
