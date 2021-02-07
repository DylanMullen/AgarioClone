package me.dylanmullen.agar.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import me.dylanmullen.agar.game.events.events.CollisionEvent;

public class Runner
{

	public static void main(String[] args)
	{
//		new AgarioClone();
		Runner runner = new Runner();
		Class<?> klass = runner.getClass();
		Method[] methods = klass.getDeclaredMethods();
		for(Method meth : methods)
			for(Parameter para : meth.getParameters())
				if(para.getType().equals(CollisionEvent.class))
					try
					{
						meth.invoke(runner, new CollisionEvent());
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}
	
	public void test(CollisionEvent event)
	{
		System.out.println("invoked");
	}

}
