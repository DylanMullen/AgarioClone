package me.dylanmullen.agar.game.ecs;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

import me.dylanmullen.agar.game.ecs.components.ControlComponent;
import me.dylanmullen.agar.game.ecs.components.RenderComponent;
import me.dylanmullen.agar.game.ecs.systems.ControlSystem;
import me.dylanmullen.agar.game.ecs.systems.RenderSystem;
import me.dylanmullen.agar.graphics.opengl.Camera;
import me.dylanmullen.agar.window.input.InputController;

public class EntityHandler
{

	private List<Entity> entities;

	private Camera camera;

	private ControlSystem controlSystem;
	private RenderSystem renderSystem;

	public EntityHandler(InputController input)
	{
		this.camera = new Camera(input);

		this.entities = new ArrayList<Entity>();
		this.controlSystem = new ControlSystem(input.getKeyboard());
		this.renderSystem = new RenderSystem(camera);
	}

	public void createPlayer()
	{
		Entity player = EntityFactory.createPlayerEntity(new Vector3f(0, 1, 0));
		controlSystem.registerComponent(player.getComponent(ControlComponent.class));
		renderSystem.registerComponent(player.getComponent(RenderComponent.class));
	}

	public void update()
	{
//		controlSystem.handle();
		camera.update();
	}

	public RenderSystem getRenderSystem()
	{
		return renderSystem;
	}

	public ControlSystem getControlSystem()
	{
		return controlSystem;
	}

}
