package items.consommables;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import effects.RestoreHeal;

public class MedaillonArmion extends Consommable{
	
	public MedaillonArmion()
	{
		super();
		this.effects.add(new RestoreHeal(9999, null));
		this.name="Medaillon d'Armion";
		try {
			this.icone = new Image("resources/Icones/medaillonArmion.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
