package me.dylanmullen.agar.game.ecs.components;

import org.joml.Matrix4f;

import me.dylanmullen.agar.graphics.opengl.Model;
import me.dylanmullen.agar.graphics.opengl.Shader;

public class RenderComponent extends Component
{

	private Shader shader;
	private Model model;
	private PositionComponent positionComponent;

	public RenderComponent(Shader shader, Model model, PositionComponent positionComponent)
	{
		this.shader = shader;
		this.model = model;
		this.positionComponent = positionComponent;
	}

	public Matrix4f getModelMatrix()
	{
		Matrix4f matrix = new Matrix4f();
		matrix.translate(positionComponent.getPosition());
		matrix.scale(model.getScale(), 0, model.getScale());
		return matrix;
	}

	public Model getModel()
	{
		return model;
	}

	public PositionComponent getPositionComponent()
	{
		return positionComponent;
	}

	public Shader getShader()
	{
		return shader;
	}

}
