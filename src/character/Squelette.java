package character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Squelette extends Ennemi {
	
	

	
	public Squelette(int diff,int nom) throws SlickException
	{
		this.atk=10;
		this.def=6;
		this.lvl=diff;
		this.pvMax=25;
		this.pv=25;
		this.loot=1;
		this.nom="Squellete "+nom;
		this.profil=1;
		this.setText("src/Combat/personnages/sprites/squellete.png");
	}
	
	public static int getLoot()
	{
		return 1;
	}

}
