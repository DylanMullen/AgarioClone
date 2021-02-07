package me.dylanmullen.agar.game.events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import me.dylanmullen.agar.game.events.events.Event;

public class EventHandler
{

	private List<EventListenerInfo> eventListeners;

	public EventHandler()
	{
		this.eventListeners = new ArrayList<EventListenerInfo>();
	}

	public void registerListener(Listener eventListener)
	{
		this.eventListeners.add(new EventListenerInfo(eventListener));
	}

	public void fireEvent(Event event)
	{
		for (EventListenerInfo listener : eventListeners)
		{
			Method method = listener.getEventMethod(event);
			if (method == null)
				continue;

			try
			{
				method.invoke(listener.getListener(), event);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
			{
				System.err.println("Failed to fire event");
			}
		}
	}

}
