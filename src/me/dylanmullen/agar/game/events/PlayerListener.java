package me.dylanmullen.agar.game.events;

import org.joml.Vector3f;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.components.HealthComponent;
import me.dylanmullen.agar.game.ecs.components.PositionComponent;
import me.dylanmullen.agar.game.ecs.components.RenderComponent;
import me.dylanmullen.agar.game.events.api.EventHandler;
import me.dylanmullen.agar.game.events.api.EventListener;
import me.dylanmullen.agar.game.events.api.Listener;
import me.dylanmullen.agar.game.events.api.event.collision.BoundsCollideEvent;
import me.dylanmullen.agar.game.events.api.event.collision.EntityCollideEvent;
import me.dylanmullen.agar.game.events.api.event.player.PlayerEatEvent;

public class PlayerListener implements Listener
{

	private Entity player;

	public PlayerListener(Entity player)
	{
		this.player = player;
	}

	@EventListener
	public void onCollision(EntityCollideEvent event)
	{
		if (event.getOwner() == null)
			return;
		if (event.getInterceptor() == null)
			return;

		Entity interceptor = GameController.getInstance().getEntityHandler().getEntity(event.getInterceptor());
		if (interceptor == null)
			return;

		if (interceptor.hasComponent(HealthComponent.class))
			EventHandler.getInstance().fireEvent(new PlayerEatEvent(player, interceptor));
	}

	@EventListener
	public void onPlayerEat(PlayerEatEvent event)
	{
		float nutrition = clamp(event.getEatableHealth().getHealth() * 0.4f, 1f);
		event.getPlayerHealth().incrementHealth(nutrition);

		((RenderComponent) event.getPlayer().getComponent(RenderComponent.class)).getModel()
				.incrementScale(nutrition / 50f);

		event.getToEat().kill();
	}

	@EventListener
	public void onBoundsCollision(BoundsCollideEvent event)
	{
		System.out.println("here");
		Entity player = GameController.getInstance().getEntityHandler().getEntity(event.getEntity());
		if (player == null)
			return;
		PositionComponent position = (PositionComponent) player.getComponent(PositionComponent.class);
		position.setPosition(new Vector3f(event.getBounds().x, 0, event.getBounds().y));
	}

	private float clamp(float value, float minimum)
	{
		return (value > minimum ? value : minimum);
	}

}
