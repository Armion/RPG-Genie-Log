package items.potions;

import effects.RestoreHeal;
import items.Item;

public class HealPot extends Item {

	public HealPot()
	{
		super();
		
		this.effects.add(new RestoreHeal(5));
	}


}
