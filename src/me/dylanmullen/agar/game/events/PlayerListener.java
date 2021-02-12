package me.dylanmullen.agar.game.events;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.components.HealthComponent;
import me.dylanmullen.agar.game.ecs.components.RenderComponent;
import me.dylanmullen.agar.game.events.api.EventHandler;
import me.dylanmullen.agar.game.events.api.EventListener;
import me.dylanmullen.agar.game.events.api.Listener;
import me.dylanmullen.agar.game.events.api.event.CollisionEvent;
import me.dylanmullen.agar.game.events.api.event.PlayerEatEvent;

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
			EventHandler.getInstance().fireEvent(new PlayerEatEvent(player, interceptor));
		}
	}

	@EventListener
	public void onPlayerEat(PlayerEatEvent event)
	{
		float nutrition = event.getEatableHealth().getHealth() * 0.4f;
		event.getPlayerHealth().incrementHealth(nutrition);
	}

}
