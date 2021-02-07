package me.dylanmullen.agar.game.events;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.dylanmullen.agar.game.events.events.Event;

public class EventHandler
{

	private Map<Class<? extends Event>, List<EventListener>> eventListeners;

	public EventHandler()
	{
		this.eventListeners = new HashMap<Class<? extends Event>, List<EventListener>>();
	}
	
	public void registerListener(EventListener event)
	{
	}

}
