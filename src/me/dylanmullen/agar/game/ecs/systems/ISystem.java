package me.dylanmullen.agar.game.ecs.systems;

import me.dylanmullen.agar.game.ecs.components.Component;

public interface ISystem
{

	public void handle();
	public void registerComponent(Component component);
	public void deregisterComponent(Component component);
	
}
