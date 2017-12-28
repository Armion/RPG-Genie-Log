package items;
import java.util.ArrayList;
import java.util.List;

import character.Entitee;
import effects.*;

public abstract class Item {
	
	protected boolean fight;
	protected float weight;
	protected String name;
	protected List<Effect> effects;
	
	
	
	public Item()
	{
		this.fight = false;
		this.weight = 0f;
		this.name = "noname";
		effects = new ArrayList<>();
	}
	
	public void utiliser(String log, Entitee cible)
	{
		for(Effect e : effects)
		{
			e.activer(log);
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
