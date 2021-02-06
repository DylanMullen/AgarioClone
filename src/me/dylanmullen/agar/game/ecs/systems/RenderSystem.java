package me.dylanmullen.agar.game.ecs.systems;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import me.dylanmullen.agar.game.ecs.components.Component;
import me.dylanmullen.agar.game.ecs.components.RenderComponent;
import me.dylanmullen.agar.graphics.opengl.Camera;
import me.dylanmullen.agar.graphics.opengl.Shader;
import me.dylanmullen.agar.graphics.opengl.VAO;

public class RenderSystem implements ISystem
{

	private static List<RenderComponent> renderComponents = new ArrayList<RenderComponent>();

	private Matrix4f projection;
	private Camera camera;

	public static Shader shader = new Shader("terrain.vert", "terrain.frag");

	public RenderSystem(Camera camera)
	{
		this.camera = camera;

		this.projection = createProjectionMatrix();

		shader.start();
		shader.setProjectionMatrix(projection);
		shader.stop();
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
		for (int i = 0; i < renderComponents.size(); i++)
		{
			RenderComponent component = renderComponents.get(i);
			Shader componentShader = component.getShader();
			componentShader.start();
			componentShader.setProjectionMatrix(projection);
			componentShader.setTransformationMatrix(component.getModelMatrix());
			componentShader.setViewMatrix(camera.getViewMatrix());
			drawVAO(component.getModel().getModelData());
			componentShader.stop();
		}
	}

	public void drawVAO(VAO vao)
	{
		GL30.glBindVertexArray(vao.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL11.glDrawElements(GL11.GL_TRIANGLES, vao.getCount(), GL11.GL_UNSIGNED_INT, 0);
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}

	public void registerComponent(Component component)
	{
		if (!(component instanceof RenderComponent))
			return;
		renderComponents.add((RenderComponent) component);
	}

	@Override
	public void deregisterComponent(Component component)
	{
		if (!(component instanceof RenderComponent))
			return;
		renderComponents.remove((RenderComponent) component);
	}
	
	
	//TODO: REMOVE THIS. Debug only.
	@Deprecated
	public static List<RenderComponent> getRenderComponents()
	{
		return renderComponents;
	}

}
