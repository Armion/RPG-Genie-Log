package items.consommables.potions;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
		
		try {
			this.icone = new Image("resources/Icones/ManaPot.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public ManaPot(int amount)
	{
		super();
		this.effects.add(new RestoreMana(amount, null));
		this.name="Potion de mana";
		this.amount = amount;
		try {
			this.icone = new Image("resources/Icones/ManaPot.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public void utiliser(Entitee cible)
	{
		for(Effect e : this.effects)
		{
			e.changeTarget(cible);
			e.activer();
		}
		
		if(this.stacks>0)
			this.stacks --;
		
		
	}

}
