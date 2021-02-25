package me.dylanmullen.agar.game.ecs.components;

import java.util.UUID;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import me.dylanmullen.agar.game.GameController;
import me.dylanmullen.agar.game.ecs.systems.ControlSystem;
import me.dylanmullen.agar.game.events.api.EventHandler;
import me.dylanmullen.agar.game.events.api.event.player.PlayerSplitEvent;
import me.dylanmullen.agar.graphics.opengl.Camera;
import me.dylanmullen.agar.window.input.InputController;
import me.dylanmullen.agar.window.input.MouseHandler;

public class ControlComponent implements Component
{

	private UUID entity;
	private PositionComponent positionComponent;

	private Vector3f velocity;

	private long lastSplit;

	public ControlComponent(UUID entity, PositionComponent position)
	{
		this.entity = entity;
		this.positionComponent = position;
		this.lastSplit = System.currentTimeMillis();
		this.velocity = new Vector3f();
	}

	public ControlComponent(UUID entity, PositionComponent position, Vector3f velocity)
	{
		this(entity, position);
		this.velocity = velocity;
	}

	@Override
	public void load()
	{
		ControlSystem.getInstance().registerComponent(this);
	}

	@Override
	public void unload()
	{
		this.positionComponent = null;
		ControlSystem.getInstance().deregisterComponent(this);
	}

	public void handleInput(InputController input)
	{
		handleMovement(input.getMouse());
		handleControls(input);
	}

	private void handleMovement(MouseHandler mouse)
	{
		if (this.positionComponent == null)
			return;

		this.positionComponent.setMoved(false);
		if (mouse.getMousePosition() == null)
			return;
		
		Vector2f mousePosition = mouse.convertToOpenGLCoords();
		Vector2f direction = mousePosition.sub(convertToOpenglSpace()).normalize();

		Vector3f movementVector = new Vector3f(direction.x, 0, direction.y);
		if (movementVector.x == 0 && movementVector.z == 0)
			return;
		
		this.velocity.add(movementVector.x, 0, movementVector.z);

		float x = clamp(mouse.distanceFromCenter() / 100, 1) * 0.2f;
		this.velocity.mul(x);
		move(movementVector);
	}

	private Vector2f convertToOpenglSpace()
	{
		Camera camera = GameController.getInstance().getCamera();
		Matrix4f projection = GameController.getInstance().getRenderSystem().getProjection();

		Vector4f vector = toEyeSpace(camera);
		vector = toClipSpace(vector, projection);
		
		System.out.println(vector.x + ":" + vector.y);

		return new Vector2f(vector.x, vector.y);
	}

	private Vector4f toEyeSpace(Camera camera)
	{
		Matrix4f viewMatrix = camera.getViewMatrix();
		return viewMatrix.transform(positionComponent.getPosition().x, positionComponent.getPosition().y,
				positionComponent.getPosition().z, 1f, new Vector4f());
	}

	private Vector4f toClipSpace(Vector4f eyeSpace, Matrix4f projection)
	{
		return projection.transform(eyeSpace.x, 1f, eyeSpace.z, 1f, new Vector4f());
	}

	private void handleControls(InputController input)
	{
		if (input.getKeyboard().wasPressed(GLFW.GLFW_KEY_SPACE) && System.currentTimeMillis() - lastSplit >= 200)
		{
			lastSplit = System.currentTimeMillis();
			EventHandler.getInstance()
					.fireEvent(new PlayerSplitEvent(entity, input.getMouse().getDirectionFromCenter()));
		}
	}

	private void move(Vector3f moveVector)
	{
		this.positionComponent.changePosition(velocity);
	}

	private float clamp(float value, float maximum)
	{
		return (value < maximum ? value : maximum);
	}

	public PositionComponent getPositionComponent()
	{
		return positionComponent;
	}
}
