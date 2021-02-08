package me.dylanmullen.agar.game.events.events;

import java.util.UUID;

public class CollisionEvent implements Event
{

	private UUID owner;
	private UUID interceptor;

	public CollisionEvent(UUID owner, UUID interceptor)
	{
		this.owner = owner;
		this.interceptor = interceptor;
	}
	
	public UUID getOwner()
	{
		return owner;
	}
	
	public UUID getInterceptor()
	{
		return interceptor;
	}

}
