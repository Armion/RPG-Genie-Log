package items;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.newdawn.slick.Image;

import character.Entitee;
import effects.*;

//classe abstraite qui doit etre implémenté par tout les items
public abstract class Item {
	
	protected boolean fight;
	protected float weight;
	protected String name;
	protected List<Effect> effects;
	protected Image icone;
	protected UUID id;
	protected int stacks;
	
	
	
	public Item()
	{
		this.fight = false;
		this.weight = 0f;
		this.name = "noname";
		effects = new ArrayList<>();
		this.icone = null;
		this.id = UUID.randomUUID();
		this.stacks = -1;
	}
	

	//methode pour utiliser un objet en utilisant tout ses ses effets
	public void utiliser(Entitee cible)
	{
		if(cible != null)
		{
			for(Effect e : effects)
			{
				e.activer();
			}
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
	
	public Image getIcone()
	{
		return this.icone;
	}
	
	public UUID getId()
	{
		return this.id;
	}
	
	public int getStacks()
	{
		return this.stacks;
	}

}
