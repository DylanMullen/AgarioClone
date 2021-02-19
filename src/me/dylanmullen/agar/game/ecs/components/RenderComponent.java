package me.dylanmullen.agar.game.ecs.components;

import java.util.HashMap;
import java.util.Map;

import org.joml.Matrix4f;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.graphics.opengl.Model;
import me.dylanmullen.agar.graphics.opengl.Shader;

public class RenderComponent implements Component
{

	private Shader shader;
	private Model model;
	private PositionComponent positionComponent;

	private Map<String, Object> customProperties;

	private boolean attribTimeRotate;

	public RenderComponent(Shader shader, Model model, PositionComponent positionComponent)
	{
		this.shader = shader;
		this.model = model;
		this.positionComponent = positionComponent;
		this.customProperties = new HashMap<String, Object>();
	}

	@Override
	public void load()
	{
		GameController.getInstance().getRenderSystem().registerComponent(this);
	}

	@Override
	public void unload()
	{
		GameController.getInstance().getRenderSystem().deregisterComponent(this);
		model.getModelData().delete();
	}

	public void setCustomProperties()
	{
		for (String location : customProperties.keySet())
		{
			Object object = customProperties.get(location);
			shader.setUniform(location, object);
		}
	}

	public void addProperty(String location, Object object)
	{
		customProperties.put(location, object);
	}

	private float angle = (float) (Math.sin(Math.random() * 100)*360);

	public Matrix4f getModelMatrix()
	{
		Matrix4f matrix = new Matrix4f();
		matrix.translate(positionComponent.getPosition());
		matrix.scale(model.getScale(), model.getScale(), model.getScale());

		if (attribTimeRotate)
		{
			matrix.rotate(angle, 0, 1, 0);
		}
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

	public void setAttribTimeRotate(boolean attribTimeRotate)
	{
		this.attribTimeRotate = attribTimeRotate;
	}

}
