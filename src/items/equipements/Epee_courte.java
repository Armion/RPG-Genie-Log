package items.equipements;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Epee_courte extends Equipement {
	
	public Epee_courte()
	{
		super();
		this.bolusDeg=5;
		this.name="Epee Courte";
		this.type=2;
		try {
			this.icone = new Image("src/resources/Icones/epee.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
