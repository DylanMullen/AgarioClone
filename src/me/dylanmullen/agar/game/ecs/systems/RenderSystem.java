package me.dylanmullen.agar.game.ecs.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import me.dylanmullen.agar.game.ecs.components.Component;
import me.dylanmullen.agar.game.ecs.components.RenderComponent;
import me.dylanmullen.agar.graphics.opengl.Camera;
import me.dylanmullen.agar.graphics.opengl.Shader;
import me.dylanmullen.agar.graphics.opengl.ShaderManager;
import me.dylanmullen.agar.graphics.opengl.VAO;

public class RenderSystem implements ISystem
{

//	private static List<RenderComponent> renderComponents = new ArrayList<RenderComponent>();

	private Map<Shader, List<RenderComponent>> renderComponents;

	private ShaderManager shaders;

	private Matrix4f projection;
	private Camera camera;

	public RenderSystem(Camera camera)
	{
		this.camera = camera;
		this.projection = createProjectionMatrix();
		this.shaders = new ShaderManager();
		this.renderComponents = new HashMap<Shader, List<RenderComponent>>();
	}

	private Matrix4f createProjectionMatrix()
	{
		Matrix4f matrix = new Matrix4f();
		matrix = matrix.ortho(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);
		return matrix;
	}

	// HIGHLY UNOPTIMIZED
	public void handle()
	{
		for (Shader shader : renderComponents.keySet())
		{
			if (renderComponents.get(shader).size() == 0)
				continue;

			shader.start();
			shader.setProjectionMatrix(projection);
			shader.setViewMatrix(camera.getViewMatrix());

			for (RenderComponent component : renderComponents.get(shader))
			{
				shader.setTransformationMatrix(component.getModelMatrix());
				component.setCustomProperties();
				drawVAO(component.getModel().getModelData());
			}

			shader.stop();
		}
	}

	private void drawVAO(VAO vao)
	{
		GL30.glBindVertexArray(vao.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL11.glDrawElements(GL11.GL_TRIANGLES, vao.getCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}

	public void registerComponent(Component component)
	{
		if (!(component instanceof RenderComponent))
			return;

		RenderComponent renderComponent = (RenderComponent) component;
		List<RenderComponent> components = renderComponents.get(renderComponent.getShader());
		if (components == null)
			components = new ArrayList<RenderComponent>();
		components.add(renderComponent);
		renderComponents.put(renderComponent.getShader(), components);
	}

	@Override
	public void deregisterComponent(Component component)
	{
		if (!(component instanceof RenderComponent))
			return;
	}

	public ShaderManager getShaders()
	{
		return shaders;
	}
}
