package me.dylanmullen.agar.game.ecs.components;

import java.util.HashMap;
import java.util.Map;

import org.joml.Matrix4f;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.game.ecs.systems.RenderSystem;
import me.dylanmullen.agar.graphics.opengl.Model;
import me.dylanmullen.agar.graphics.opengl.Shader;

public class RenderComponent implements Component
{

	private Shader shader;
	private Model model;
	private PositionComponent positionComponent;

	private Map<String, Object> customProperties;

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
			shader.setUniform(location, customProperties.get(location));
	}

	public void addProperty(String location, Object object)
	{
		customProperties.put(location, object);
	}

	public Matrix4f getModelMatrix()
	{
		Matrix4f matrix = new Matrix4f();
		matrix.translate(positionComponent.getPosition());
		matrix.scale(model.getScale(), model.getScale(), model.getScale());
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
