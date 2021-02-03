package me.dylanmullen.agar.core;

import me.dylanmullen.agar.window.Window;

public class AgarioClone
{

	private Window window;

	private Thread thread;
	private GameLoop gameLoop;

	public AgarioClone()
	{
		startApplication();
	}

	public synchronized boolean startApplication()
	{
		Thread thread = new Thread(gameLoop = new GameLoop(this));
		gameLoop.setRunning(true);
		thread.start();
		return true;
	}

	public synchronized boolean stopApplication() throws InterruptedException
	{
		if (gameLoop == null)
			return false;
		if (!gameLoop.isRunning())
			return false;

		gameLoop.setRunning(false);
		thread.join();
		return true;
	}

	public Window getWindow()
	{
		return window;
	}

	public void setWindow(Window window2)
	{
		this.window = window2;
	}

}
