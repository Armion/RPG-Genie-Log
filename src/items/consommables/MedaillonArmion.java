package items.consommables;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import character.Entitee;
import effects.Effect;
import effects.RestoreHeal;
import effects.RestoreMana;


//car y'en a marre de se faire casser en deux par les mobs !
public class MedaillonArmion extends Consommable{
	
	public MedaillonArmion()
	{
		super();
		this.effects.add(new RestoreHeal(9999, null));
		this.effects.add(new RestoreMana(9999, null));
		this.name="Medaillon d'Armion";
		try {
			this.icone = new Image("resources/Icones/medaillonArmion.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.stacks = -1;
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
