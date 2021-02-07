package me.dylanmullen.agar.game.ecs.systems;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import me.dylanmullen.agar.game.collision.SquareCollision;
import me.dylanmullen.agar.game.ecs.components.CollisionComponent;
import me.dylanmullen.agar.game.ecs.components.Component;
import me.dylanmullen.agar.game.ecs.components.PositionComponent;

public class CollisionSystem implements ISystem
{

	private List<CollisionComponent> components;

	public CollisionSystem()
	{
		this.components = new ArrayList<CollisionComponent>();

		CollisionComponent component = new CollisionComponent(new PositionComponent(new Vector3f(0, 0, 0)),
				new SquareCollision(new Vector2f(-1, 1), new Vector2f(1, -1)));
		registerComponent(component);
	}

	@Override
	public void handle()
	{
		// TODO: REMOVE THIS O(n^2)
		for (CollisionComponent current : components)
		{
			for (CollisionComponent target : components)
			{
				if (current.equals(target))
					continue;
				if (current.getPosition().hasMoved())
					current.updatePosition(current.getPosition().getMovementVector());
//				if (target.getCollision().collide(current.getCollision()))
					
			}
		}
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
