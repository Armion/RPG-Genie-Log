package items.equipements;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Armure_leg extends Equipement {
	
	
	public Armure_leg()
	{
		super();
		this.bolusDef=5;
		this.bolusPV=10;
		this.bolusMana=-10;
		this.name="Armure légère";
		this.type=1;
		try {
			this.icone = new Image("src/resources/Icones/armure.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
