package items.consommables.potions;

import character.Entitee;
import effects.Effect;
import effects.RestoreHeal;
import effects.RestoreMana;
import items.consommables.Consommable;

public class ManaPot extends Consommable{
	
private int amount;
	
	public ManaPot()
	{
		super();
		this.effects.add(new RestoreMana(5, null));
		this.name="Potion de mana";
	}
	
	public ManaPot(int amount)
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
