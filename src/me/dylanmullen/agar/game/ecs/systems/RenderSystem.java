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
import me.dylanmullen.agar.graphics.opengl.VAO;

public class RenderSystem implements ISystem
{

	private List<RenderComponent> renderComponents;

	private Matrix4f projection;
	private Camera camera;

	public RenderSystem(Camera camera)
	{
		this.camera = camera;

		this.renderComponents = new ArrayList<RenderComponent>();
		this.projection = createProjectionMatrix();
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
			component.getShader().start();
			component.getShader().setProjectionMatrix(projection);
			component.getShader().setTransformationMatrix(component.getModelMatrix());
			component.getShader().setViewMatrix(camera.getViewMatrix());
			drawVAO(component.getModel().getModelData());
			component.getShader().stop();
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

	@Override
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

}
