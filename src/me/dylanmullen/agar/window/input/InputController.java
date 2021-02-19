package me.dylanmullen.agar.window.input;

import java.awt.Dimension;

public class InputController
{

	private static InputController instance;

	private KeyboardHandler keyboard;
	private MouseHandler mouse;

	public InputController(Dimension dimensions, long windowRef)
	{
		if (instance == null)
			instance = this;
		init(dimensions, windowRef);
	}

	public static InputController getInstance()
	{
		return instance;
	}

	private void init(Dimension dimension, long window)
	{
		this.keyboard = new KeyboardHandler(window);
		this.mouse = new MouseHandler(dimension, window);
	}

	public KeyboardHandler getKeyboard()
	{
		return keyboard;
	}

	public MouseHandler getMouse()
	{
		return mouse;
	}

}
