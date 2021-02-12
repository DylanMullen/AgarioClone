package me.dylanmullen.agar.game.ecs.components;

import me.dylanmullen.agar.game.ecs.systems.RenderSystem;

public class HealthComponent implements Component
{

	private float health;

	public HealthComponent(float baseHealth)
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

	public void incrementHealth(float toIncrease)
	{
		this.health += toIncrease;
	}

	public void decrementHealth(float toDecrease)
	{
		this.health += toDecrease;
	}

	public void zero()
	{
		this.health = 0;
	}

	public float getHealth()
	{
		return health;
	}

}
