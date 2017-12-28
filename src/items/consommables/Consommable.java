package items.consommables;

import character.Entitee;
import items.Item;

public abstract class Consommable extends Item {

	protected Entitee cible;
	protected boolean typeTarget; // true ennemies, false alli�s
	
	
	
	public Consommable()
	{
		super();
		this.fight = true;
		this.cible = null;
	}
	
	public boolean isTargatable()
	{
		return this.typeTarget;
	}
	
}