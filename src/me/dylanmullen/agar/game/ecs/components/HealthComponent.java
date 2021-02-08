package me.dylanmullen.agar.game.ecs.components;

import me.dylanmullen.agar.game.ecs.systems.RenderSystem;

public class HealthComponent implements Component
{

	private double health;

	public HealthComponent(double baseHealth)
	{
		this.health = baseHealth;
	}

	@Override
	public void load()
	{
	}

	@Override
	public void unload()
	{
		health = 0;
	}

	public void incrementHealth(double toIncrease)
	{
		this.health += toIncrease;
	}

	public void decrementHealth(double toDecrease)
	{
		this.health += toDecrease;
	}

	public void zero()
	{
		this.health = 0;
	}

	public double getHealth()
	{
		return health;
	}

}
