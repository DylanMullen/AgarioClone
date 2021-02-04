package me.dylanmullen.agar.game.ecs.systems;

import java.util.ArrayList;
import java.util.List;

import me.dylanmullen.agar.game.ecs.components.RenderComponent;
import me.dylanmullen.agar.graphics.opengl.VAO;

public class RenderSystem
{

	private List<RenderComponent> renderComponents;

	public RenderSystem()
	{
		this.renderComponents = new ArrayList<RenderComponent>();

	}

	public void handle()
	{
		for (int i = 0; i < renderComponents.size(); i++)
		{
			RenderComponent component = renderComponents.get(i);
			component.getShader().start();
			drawVAO(component.getModelInfo());
			component.getShader().stop();
		}
	}

	public void drawVAO(VAO vao)
	{
		//TEST
	}
	
}
