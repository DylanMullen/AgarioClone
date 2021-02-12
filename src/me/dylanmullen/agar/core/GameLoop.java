package me.dylanmullen.agar.core;

import static org.lwjgl.glfw.GLFW.glfwSwapInterval;

import java.awt.Dimension;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.window.Window;

public class GameLoop implements Runnable
{

	private AgarioClone app;
	private boolean running;

	private GameController gameController;

	public GameLoop(AgarioClone app)
	{
		this.app = app;
		this.running = false;
	}

	@Override
	public void run()
	{
		init();

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

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

	private void init()
	{
		app.setWindow(new Window("Agario Clone", new Dimension(1280, 720)));
		app.getWindow().createWindow();

		GL.createCapabilities();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0, 0, 0, 1f);
		
        glfwSwapInterval(1);


		this.gameController = new GameController(app.getWindow().getInputController());
	}

	public void update()
	{
		GLFW.glfwPollEvents();
		gameController.update();
	}

	public void render()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		if (app.getWindow().getInputController().getKeyboard().isPressed(GLFW.GLFW_KEY_X))
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		else
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);

		gameController.render();

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
