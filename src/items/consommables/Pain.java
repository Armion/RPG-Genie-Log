package items.consommables;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import character.Entitee;
import effects.Effect;
import effects.RestoreHeal;
import effects.RestoreMana;

public class Pain extends Consommable{
	
	
	public Pain()
	{
		super();
		this.fight = false;
		this.effects.add(new RestoreHeal(15, null));
		this.effects.add(new RestoreMana(15, null));
		this.name="Pain";
		try {
			this.icone = new Image("resources/Icones/Bread.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.stacks = 3;
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
