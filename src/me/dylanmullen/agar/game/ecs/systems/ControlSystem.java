package me.dylanmullen.agar.game.ecs.systems;

import java.util.ArrayList;
import java.util.List;

import me.dylanmullen.agar.game.ecs.components.Component;
import me.dylanmullen.agar.game.ecs.components.ControlComponent;
import me.dylanmullen.agar.window.input.KeyboardHandler;

public class ControlSystem implements ISystem
{

	private static ControlSystem instance;
	
	private KeyboardHandler keyboard;
	private List<ControlComponent> controlComponents;

	public ControlSystem(KeyboardHandler keyboard)
	{
		if(instance ==null)
			instance = this;
		this.keyboard = keyboard;
		this.controlComponents = new ArrayList<ControlComponent>();
	}
	
	public static ControlSystem getInstance()
	{
		return instance;
	}

	public void handle()
	{
		for (int i = 0; i < controlComponents.size(); i++)
		{
			ControlComponent component = controlComponents.get(i);
			component.handleInput(keyboard);
		}
	}

	@Override
	public void registerComponent(Component component)
	{
		if (!(component instanceof ControlComponent))
			return;
		controlComponents.add((ControlComponent) component);
	}

	@Override
	public void deregisterComponent(Component component)
	{
		if (!(component instanceof ControlComponent))
			return;
		controlComponents.add((ControlComponent) component);
	}

}
