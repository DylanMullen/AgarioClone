package me.dylanmullen.agar.game.events.api.event.player;

import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.components.HealthComponent;
import me.dylanmullen.agar.game.events.api.Event;

public class PlayerEatEvent implements Event
{

	private Entity player;
	private Entity toEat;

	public PlayerEatEvent(Entity player, Entity toEat)
	{
		this.player = player;
		this.toEat = toEat;
	}

	public Entity getPlayer()
	{
		return player;
	}

	public Entity getToEat()
	{
		return toEat;
	}

	public HealthComponent getPlayerHealth()
	{
		return (HealthComponent) player.getComponent(HealthComponent.class);
	}

	public HealthComponent getEatableHealth()
	{
		return (HealthComponent) toEat.getComponent(HealthComponent.class);
	}

}
