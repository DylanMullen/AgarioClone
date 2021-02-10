package me.dylanmullen.agar.game.events.api;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import me.dylanmullen.agar.game.events.api.event.Event;

public class EventListenerInfo
{

	private Listener listener;
	private Map<Parameter, Method> eventListeners;

	public EventListenerInfo(Listener listener)
	{
		this.listener = listener;
		this.eventListeners = new HashMap<Parameter, Method>();
		load();
	}

	private void load()
	{
		Class<?> klass = listener.getClass();

		for (Method method : klass.getDeclaredMethods())
		{
			if (!method.isAnnotationPresent(EventListener.class))
				continue;

			if (method.getParameters().length > 1)
				continue;

			Parameter parameters = method.getParameters()[0];
			if (!(Event.class.isAssignableFrom(parameters.getType())))
				continue;
			eventListeners.put(parameters, method);
		}
	}

	public Method getEventMethod(Event event)
	{
		for (Parameter parameter : eventListeners.keySet())
		{
			if (parameter.getType().equals(event.getClass()))
				return eventListeners.get(parameter);
		}
		return null;
	}

	public Listener getListener()
	{
		return listener;
	}

}
