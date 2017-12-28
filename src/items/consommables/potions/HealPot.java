package items.consommables.potions;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
		this.name="Potion de soins";
		try {
			this.icone = new Image("resources/Icones/HealPot.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public HealPot(int amount)
	{
		super();
		this.name="Potion de soins";
		this.effects.add(new RestoreHeal(amount, null));
		this.amount = amount;
		try {
			this.icone = new Image("resources/Icones/HealPot.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.typeTarget=false;
	}
	
	
	
	@Override
	public void utiliser(String log, Entitee cible)
	{
		for(Effect e : this.effects)
		{
			e.changeTarget(cible);
			e.activer(log);
		}
		
		if(this.stacks>0)
			this.stacks --;
		
		
		System.out.println(log);
	}
	


}
