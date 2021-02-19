package me.dylanmullen.agar.game.ecs;

import java.util.ArrayList;
import java.util.List;

public class EntityStack
{

	private Entity owner;
	private List<Entity> minions;

	public EntityStack(Entity owner)
	{
		this.owner = owner;
		this.minions = new ArrayList<Entity>();
	}
	
	public void addMinion(Entity entity)
	{
		if(minions.contains(entity))
			return;
		
		minions.add(entity);
	}
	
	public void removeMinion(Entity entity)
	{
		if(!minions.contains(entity))
			return;
		
		minions.remove(entity);
	}

	public Entity getOwner()
	{
		return owner;
	}
	
	public List<Entity> getMinions()
	{
		return minions;
	}
	
}
