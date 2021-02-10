package me.dylanmullen.agar.game.events;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.components.HealthComponent;
import me.dylanmullen.agar.game.ecs.components.RenderComponent;
import me.dylanmullen.agar.game.events.api.EventListener;
import me.dylanmullen.agar.game.events.api.Listener;
import me.dylanmullen.agar.game.events.api.event.CollisionEvent;

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
		if (interceptor == null)
			return;
		
		if (interceptor.hasComponent(HealthComponent.class))
		{
			HealthComponent playerHealth = (HealthComponent) player.getComponent(HealthComponent.class);
			HealthComponent interceptorHealth = (HealthComponent) interceptor.getComponent(HealthComponent.class);
			playerHealth.incrementHealth(interceptorHealth.getHealth());
			RenderComponent render = (RenderComponent) player.getComponent(RenderComponent.class);
			render.getModel().setScale(10f);
		}
	}

}
