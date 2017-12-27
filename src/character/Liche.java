package character;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import competences.Competence;
import competences.Eclair_givre;

public class Liche extends Ennemi {
	
	


	
	public Liche(int diff,int nom) throws SlickException
	{
		this.atk=3;
		this.def=3;
		this.lvl=diff;
		this.pvMax=25;
		this.pv=25;
		this.loot=1;
		this.nom="Liche "+nom;
		this.profil=1;
		this.manaMax=200;
		this.mana=200;
		this.sorts=new ArrayList<Competence> ();
		this.sorts.add(new Eclair_givre());
		
		this.setText("src/Combat/personnages/sprites/liche.png");
	}
	
	public static int getLoot()
	{
		return 1;
	}
	
	
	
	
	
	

}
