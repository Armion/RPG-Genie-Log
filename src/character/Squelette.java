package character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Squelette extends Ennemi {
	
	

	
	public Squelette(int diff,int nom) throws SlickException
	{
		this.atk=10+((diff-1)*3);
		this.def=6+((diff-1)*2);
		this.lvl=diff;
		this.pvMax=25+((diff-1)*4);
		this.pv=this.pvMax;
		this.loot=1;
		this.nom="Squellete "+nom;
		this.profil=1;
		this.setText("src/Combat/personnages/sprites/slime.png");
	}


}
