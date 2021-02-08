package me.dylanmullen.agar.game;

import org.joml.Vector3f;

import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.EntityFactory;
import me.dylanmullen.agar.game.ecs.EntityHandler;
import me.dylanmullen.agar.game.ecs.systems.CollisionSystem;
import me.dylanmullen.agar.game.ecs.systems.ControlSystem;
import me.dylanmullen.agar.game.ecs.systems.RenderSystem;
import me.dylanmullen.agar.game.map.TerrainController;
import me.dylanmullen.agar.graphics.opengl.Camera;
import me.dylanmullen.agar.window.input.InputController;

public class GameController
{

	private Camera camera;

	private EntityHandler entityHandler;

	private RenderSystem renderSystem; // TODO new thread
	private CollisionSystem collisionSystem;
	private ControlSystem controlSystem;

	private TerrainController terrainController;

	public GameController(InputController input)
	{
		init(input);
	}

	private void init(InputController input)
	{
		this.camera = new Camera(input);
		this.entityHandler = new EntityHandler(input);
		this.renderSystem = new RenderSystem(camera);
		this.collisionSystem = new CollisionSystem();
		this.controlSystem = new ControlSystem(input.getKeyboard());
		this.terrainController = new TerrainController();

		Entity player = EntityFactory.createPlayerEntity(new Vector3f(0, 0, 0));
		this.entityHandler.addEntity(player);
		this.entityHandler.setFocusedEntity(player);
	}

	public void update()
	{
		controlSystem.handle();
		collisionSystem.handle();

		camera.update();

		if (entityHandler.getFocusedEntity() != null)
			this.terrainController.handleControlledEntity(entityHandler.getFocusedEntity());
	}

	public void render()
	{
		renderSystem.handle();
	}

	public EntityHandler getEntityHandler()
	{
		return entityHandler;
	}

	public TerrainController getTerrainController()
	{
		return terrainController;
	}
}
