package me.dylanmullen.agar.game.events;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.components.HealthComponent;
import me.dylanmullen.agar.game.ecs.components.PositionComponent;
import me.dylanmullen.agar.game.events.events.CollisionEvent;

public class PlayerListener implements Listener
{

	private Entity player;

	public PlayerListener(Entity player)
	{
		this.player = player;
	}

	@EventListener
	public void onCollision(CollisionEvent event)
	{
		if (event.getOwner() == null)
			return;

		if (event.getInterceptor() == null)
			return;

		Entity interceptor = GameController.getInstance().getEntityHandler().getEntity(event.getInterceptor());
		if (interceptor.hasComponent(PositionComponent.class))
			System.out.println("eating");
	}

}
