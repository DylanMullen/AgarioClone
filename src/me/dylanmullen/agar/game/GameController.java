package me.dylanmullen.agar.game;

import org.joml.Vector3f;

import me.dylanmullen.agar.game.ecs.Entity;
import me.dylanmullen.agar.game.ecs.EntityFactory;
import me.dylanmullen.agar.game.ecs.EntityHandler;
import me.dylanmullen.agar.game.ecs.systems.CollisionSystem;
import me.dylanmullen.agar.game.ecs.systems.ControlSystem;
import me.dylanmullen.agar.game.ecs.systems.RenderSystem;
import me.dylanmullen.agar.game.events.PlayerListener;
import me.dylanmullen.agar.game.events.api.EventHandler;
import me.dylanmullen.agar.game.map.TerrainController;
import me.dylanmullen.agar.graphics.opengl.Camera;
import me.dylanmullen.agar.window.input.InputController;

public class GameController
{

	private static GameController instance;

	private Camera camera;

	private EntityHandler entityHandler;

	private RenderSystem renderSystem; // TODO new thread
	private CollisionSystem collisionSystem;
	private ControlSystem controlSystem;

	private TerrainController terrainController;
	private PlayerListener listener;

	public GameController(InputController input)
	{
		if (instance == null)
			instance = this;
		init(input);
	}

	public static GameController getInstance()
	{
		return instance;
	}

	private void init(InputController input)
	{
		this.camera = new Camera(input);
		this.entityHandler = new EntityHandler(input);
		this.renderSystem = new RenderSystem(camera);
		this.collisionSystem = new CollisionSystem();
		this.controlSystem = new ControlSystem(input.getKeyboard());
		this.terrainController = new TerrainController();

		Entity player = EntityFactory.createPlayerEntity(new Vector3f(0, 1, 0));
		this.entityHandler.addEntity(player);
		this.entityHandler.setFocusedEntity(player);
		this.camera.focusEntity(player);

		this.listener = new PlayerListener(player);
		EventHandler.getInstance().registerListener(listener);
	}

	public void update()
	{
		controlSystem.handle();
		collisionSystem.handle();

		camera.update();
		terrainController.update();
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

	public CollisionSystem getCollisionSystem()
	{
		return collisionSystem;
	}

	public Camera getCamera()
	{
		return camera;
	}

	public ControlSystem getControlSystem()
	{
		return controlSystem;
	}

	public RenderSystem getRenderSystem()
	{
		return renderSystem;
	}
}
