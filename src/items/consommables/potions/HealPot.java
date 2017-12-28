package items.consommables.potions;

import character.Entitee;
import effects.Effect;
import effects.RestoreHeal;
import items.Item;
import items.consommables.Consommable;

public class HealPot extends Consommable {

	private int amount;
	
	public HealPot()
	{
		super();
		this.effects.add(new RestoreHeal(5, null));
	}
	
	public HealPot(int amount)
	{
		this();
		this.amount = amount;
		
	}
	
	
	
	@Override
	public void utiliser(String log, Entitee cible)
	{
		for(Effect e : this.effects)
		{
			e.changeTarget(cible);
			e.activer(log);
		}
		
		
	}
	


}
