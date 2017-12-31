package items.consommables;

import character.Entitee;
import items.Item;

public abstract class Consommable extends Item {

	protected Entitee cible;
	protected boolean typeTarget; // true ennemies, false alliés
	protected int zone;
	
	
	public Consommable()
	{
		super();
		this.fight = true;
		this.cible = null;
		this.stacks = 1;
		this.zone=1;
		
	}
	
	
	@Override
	public void utiliser(Entitee cible)
	{
		super.utiliser(cible);
		if(this.stacks >0)
			this.stacks --;
		
	}
	
	
	public boolean isTargatable()
	{
		return this.typeTarget;
	}
	
}
