package items;
import java.util.ArrayList;
import java.util.List;

import effects.*;

public abstract class Item {
	
	private boolean fight;
	private float weight;
	private String name;
	protected List<Effect> effects;
	
	
	
	public Item()
	{
		this.fight = false;
		this.weight = 0f;
		this.name = "noname";
		effects = new ArrayList<>();
	}
	
	public void utiliser(String log)
	{
		for(Effect e : effects)
		{
			e.activer();
		}
	}
	
	
	//getters et setters
	public boolean fightUsable() {
		return fight;
	}
	public void setFight(boolean fight) {
		this.fight = fight;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
