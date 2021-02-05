package me.dylanmullen.agar.core;

import java.awt.Dimension;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import me.dylanmullen.agar.game.ecs.EntityHandler;
import me.dylanmullen.agar.game.map.Chunk;
import me.dylanmullen.agar.graphics.opengl.Camera;
import me.dylanmullen.agar.graphics.opengl.Shader;
import me.dylanmullen.agar.window.Window;

public class GameLoop implements Runnable
{

	private AgarioClone app;
	private boolean running;

	private Camera camera;
	private Matrix4f projection;
	private Chunk chunk, player;

	private Shader shader ;

	private EntityHandler entityHandler;

	public GameLoop(AgarioClone app)
	{
		this.app = app;
		this.running = false;
	}

	@Override
	public void run()
	{
		initGLFW();

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		this.entityHandler = new EntityHandler(app.getWindow().getInputController());
		entityHandler.createPlayer();

		while (!GLFW.glfwWindowShouldClose(app.getWindow().getWindowReference()))
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1)
			{
				update();
				delta--;
			}

			render();
		}
	}

	private void initGLFW()
	{
		app.setWindow(new Window("Agario Clone", new Dimension(1280, 720)));
		app.getWindow().createWindow();

		GL.createCapabilities();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0, 0, 0, 1f);
	}

	public void update()
	{
		GLFW.glfwPollEvents();
		entityHandler.update();
	}

	public void render()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		if (app.getWindow().getInputController().getKeyboard().isPressed(GLFW.GLFW_KEY_X))
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		else
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);

		entityHandler.getRenderSystem().handle();

		GLFW.glfwSwapBuffers(app.getWindow().getWindowReference());

	}

	public boolean isRunning()
	{
		return running;
	}

	public void setRunning(boolean running)
	{
		this.running = running;
	}

}
