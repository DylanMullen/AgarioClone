package me.dylanmullen.agar.game.ecs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.joml.Vector3f;

import me.dylanmullen.agar.window.input.InputController;

public class EntityHandler
{

	private Entity focusedEntity;

	private List<Entity> entities;

	public EntityHandler(InputController input)
	{
		this.focusedEntity = null;
		this.entities = new ArrayList<Entity>();
	}

	public void addEntity(Entity entity)
	{
		if (getEntity(entity.getUUID()) != null)
			return;
		entities.add(entity);
	}

	public void removeEntity(Entity entity)
	{
		if (getEntity(entity.getUUID()) == null)
			return;
		entities.remove(entity);
	}

	public Entity getEntity(UUID uuid)
	{
		for (Entity entity : entities)
			if (entity.getUUID().equals(uuid))
				return entity;
		return null;
	}

	public Entity getFocusedEntity()
	{
		return focusedEntity;
	}

	public void setFocusedEntity(Entity focusedEntity)
	{
		this.focusedEntity = focusedEntity;
	}

}
