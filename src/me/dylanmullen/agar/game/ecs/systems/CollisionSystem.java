package me.dylanmullen.agar.game.ecs.systems;

import java.util.ArrayList;
import java.util.List;

import me.dylanmullen.agar.game.ecs.components.CollisionComponent;
import me.dylanmullen.agar.game.ecs.components.Component;

public class CollisionSystem implements ISystem
{

	private List<CollisionComponent> components;

	public CollisionSystem()
	{
		this.components = new ArrayList<CollisionComponent>();
	}

	@Override
	public void handle()
	{
		// TODO: REMOVE THIS O(n^2)
		for (CollisionComponent current : components)
			for (CollisionComponent target : components)
			{
				if (current.equals(target))
					return;
				if (current.getCollision().collide(target.getCollision()))
					System.out.println("colliding");
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
