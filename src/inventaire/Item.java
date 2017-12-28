package inventaire;
import java.util.List;

import effects.*;

public abstract class Item {
	
	private boolean fight;
	private float weight;
	private String name;
	protected List<Effect> effects;
	
	
	
	
	
	//getters et setters
	public boolean isFight() {
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
