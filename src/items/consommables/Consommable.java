package items.consommables;

import character.Entitee;
import items.Item;

public abstract class Consommable extends Item {

	protected Entitee cible;
	protected boolean typeTarget; // true ennemies, false alliés
	
	
	
	public Consommable()
	{
		super();
		this.fight = true;
		this.cible = null;
		this.stacks = 1;
	}
	
	
	@Override
	public void utiliser(String log, Entitee cible)
	{
		super.utiliser(log, cible);
		if(this.stacks >0)
			this.stacks --;
		
	}
	
	
	public boolean isTargatable()
	{
		return this.typeTarget;
	}
	
}
