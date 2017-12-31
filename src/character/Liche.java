package character;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import competences.Abime;
import competences.Competence;
import competences.Eclair_givre;
import competences.Fumigene;
import competences.LanceFlamme;
import competences.Pluie_Feu;
import competences.SRituel;
import competences.Smite;

public class Liche extends Ennemi {
	
	


	
	public Liche(int diff,int nom) throws SlickException
	{
		this.atk=3+((diff-1)*1);
		this.def=3+((diff-1)*1);
		this.lvl=diff;
		this.pvMax=25+((diff-1)*2);
		this.pv=this.pvMax;
		this.loot=2;
		this.nom="Liche "+nom;
		this.profil=1;
		this.manaMax=200+((diff-1)*30);
		this.mana=this.manaMax;
		this.sorts=new ArrayList<Competence> ();
		this.sorts.add(new Eclair_givre());
		this.sorts.add(new LanceFlamme());
		
		
		
		if(diff>=3)
		{
			this.sorts.add(new SRituel());
		}
		
		this.setText("src/Combat/personnages/sprites/liche.png");
	}
	
	
	
	
	

}
