package me.dylanmullen.agar.game.ecs.components;

public class HealthComponent extends Component
{

	private double health;

	public HealthComponent(double baseHealth)
	{
		this.health = baseHealth;
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
