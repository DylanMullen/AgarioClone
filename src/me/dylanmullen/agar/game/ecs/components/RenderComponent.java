package me.dylanmullen.agar.game.ecs.components;

import me.dylanmullen.agar.graphics.opengl.Shader;
import me.dylanmullen.agar.graphics.opengl.VAO;

public class RenderComponent extends Component
{

	private Shader shader;
	private VAO modelInfo;
	private PositionComponent positionComponent;

	public RenderComponent(Shader shader, VAO model, PositionComponent positionComponent)
	{
		this.shader = shader;
		this.modelInfo = model;
		this.positionComponent = positionComponent;
	}

	public VAO getModelInfo()
	{
		return modelInfo;
	}

}
