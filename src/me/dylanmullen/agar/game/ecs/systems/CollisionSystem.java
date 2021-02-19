package me.dylanmullen.agar.game.ecs.systems;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.game.collision.Collision;
import me.dylanmullen.agar.game.ecs.components.CollisionComponent;
import me.dylanmullen.agar.game.ecs.components.Component;
import me.dylanmullen.agar.game.events.api.EventHandler;
import me.dylanmullen.agar.game.events.api.event.collision.BoundsCollideEvent;
import me.dylanmullen.agar.game.events.api.event.collision.EntityCollideEvent;

public class CollisionSystem implements ISystem
{

	private List<CollisionComponent> components;

	private List<Vector2f> bounds;

	public CollisionSystem()
	{
		this.components = new ArrayList<CollisionComponent>();
		this.bounds = new ArrayList<Vector2f>();
		loadBounds();
	}

	private void loadBounds()
	{
		float width = GameController.getInstance().getTerrainController().getTerrain().getTerrainWidth();
		bounds.add(new Vector2f(2, 0));
	}

	@Override
	public void handle()
	{
		// TODO: REMOVE THIS O(n^2)
		CollisionComponent current = (CollisionComponent) GameController.getInstance().getEntityHandler()
				.getFocusedEntity().getComponent(CollisionComponent.class);

		if (current.getPosition().hasMoved())
			current.updatePosition(current.getPosition().getMovementVector());

//		if (checkOutOfBounds(current.getCollision()))
//			EventHandler.getInstance().fireEvent(new BoundsCollideEvent(current.getEntityUUID(), bounds.get(0)));

		for (CollisionComponent target : components)
		{
			if (current.equals(target))
				continue;

			if (current.getCollision().collide(target.getCollision()))
			{
				EventHandler.getInstance()
						.fireEvent(new EntityCollideEvent(current.getEntityUUID(), target.getEntityUUID()));
			}

		}
	}

	private boolean checkOutOfBounds(Collision collision)
	{
		for (Vector2f position : bounds)
			if (collision.collide(position))
				return true;
		return false;
	}

	@Override
	public void registerComponent(Component component)
	{
		if (!(component instanceof CollisionComponent))
			return;
		components.add((CollisionComponent) component);
	}

	@Override
	public void deregisterComponent(Component component)
	{
		if (!(component instanceof CollisionComponent))
			return;
		components.remove((CollisionComponent) component);
	}
}
