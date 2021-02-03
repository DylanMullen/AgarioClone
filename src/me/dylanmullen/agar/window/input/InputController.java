package me.dylanmullen.agar.window.input;

public class InputController
{

	private static InputController instance;

	private KeyboardHandler keyboard;
	private MouseHandler mouse;

	public InputController(long windowRef)
	{
		if (instance == null)
			instance = this;
		init(windowRef);
	}

	public static InputController getInstance()
	{
		return instance;
	}

	private void init(long window)
	{
		this.keyboard=new KeyboardHandler(window);
		this.mouse=new MouseHandler(window);
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
