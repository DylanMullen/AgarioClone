package me.dylanmullen.agar.game.events.api.event.collision;

import java.util.UUID;

import me.dylanmullen.agar.game.events.api.Event;

public class EntityCollideEvent implements Event
{

	private UUID owner;
	private UUID interceptor;

	public EntityCollideEvent(UUID owner, UUID interceptor)
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
