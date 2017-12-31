package items.equipements;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Amulette extends Equipement {
	
	public Amulette()
	{
		super();
		this.bolusMana=50;
		this.name="Amulette";
		this.type=4;
		try {
			this.icone = new Image("src/resources/Icones/Amulette.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
