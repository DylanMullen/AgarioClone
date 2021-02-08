package me.dylanmullen.agar.game.ecs;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.dylanmullen.agar.game.ecs.components.Component;

public class Entity
{

	private UUID uuid;
	private List<Component> components;

	public Entity()
	{
		this.uuid = UUID.randomUUID();
		this.components = new ArrayList<Component>();
	}

	public void addComponent(Component component)
	{
		this.getComponents().add(component);
	}

	public void removeComponent(Component component)
	{
		this.getComponents().remove(component);
	}

	public Component getComponent(Class<? extends Component> clazz)
	{
		for (Component comp : getComponents())
			if (comp.getClass().equals(clazz))
				return comp;
		return null;
	}

	public boolean hasComponent(Class<? extends Component> klass)
	{
		return getComponent(klass) != null;
	}

	public UUID getUUID()
	{
		return uuid;
	}

	public List<Component> getComponents()
	{
		return components;
	}

}
