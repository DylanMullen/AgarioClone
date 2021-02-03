package me.dylanmullen.agar.window.input;

public class Key
{

	private int keycode;
	private boolean pressed;
	private boolean repeat;

	public Key(int keycode)
	{
		this.keycode = keycode;
	}
	
	public void setPressed(boolean pressed)
	{
		this.pressed = pressed;
	}
	
	public void setRepeat(boolean repeat)
	{
		this.repeat = repeat;
	}
	
	public boolean isPressed()
	{
		return pressed;
	}
	
	public boolean isRepeat()
	{
		return repeat;
	}

	public int getKeycode()
	{
		return keycode;
	}

}
