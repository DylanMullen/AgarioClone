package me.dylanmullen.agar.window.input;

import org.lwjgl.glfw.GLFW;

public class Button
{

	private int buttonCode;

	private boolean clicked;
	private long lastClicked;

	public Button(int buttonCode)
	{
		this.buttonCode = buttonCode;
	}

	public void setClicked(int action)
	{
		this.clicked = (action == GLFW.GLFW_PRESS) || (action == GLFW.GLFW_REPEAT);
		setLastClicked((clicked ? System.currentTimeMillis() : -1));
	}

	public int getButtonCode()
	{
		return buttonCode;
	}

	public void setLastClicked(long lastClicked)
	{
		this.lastClicked = lastClicked;
	}

	public boolean isClicked()
	{
		return clicked;
	}

}
