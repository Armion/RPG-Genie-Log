package items.equipements;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Dague extends Equipement {
	
	public Dague()
	{
		super();
		this.bolusDef=-5;
		this.bolusDeg=10;
		this.name="Dague";
		this.type=2;
		try {
			this.icone = new Image("src/resources/Icones/dague.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
