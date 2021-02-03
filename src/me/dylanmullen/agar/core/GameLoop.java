package me.dylanmullen.agar.core;

import java.awt.Dimension;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import me.dylanmullen.agar.window.Window;

public class GameLoop implements Runnable
{

	private AgarioClone app;
	private boolean running;

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
		app.setWindow(new Window("Agario Clone", new Dimension(1280,720)));
		app.getWindow().createWindow();
		
		GL.createCapabilities();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	public void update()
	{
		GLFW.glfwPollEvents();

	}

	public void render()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
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
