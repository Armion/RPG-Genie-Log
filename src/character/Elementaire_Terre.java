package character;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import competences.Abime;
import competences.Charge;
import competences.Competence;
import competences.LanceFlamme;
import competences.Pluie_Feu;

public class Elementaire_Terre extends Ennemi {
	
	
	public Elementaire_Terre(int diff,int nom) throws SlickException
	{
		this.atk=10+((diff-1)*2);
		this.def=10+((diff-1)*2);
		this.lvl=diff;
		this.pvMax=25+((diff-1)*4);
		this.pv=this.pvMax;
		this.loot=2;
		this.nom="Elementaire de Terre "+nom;
		this.profil=1;
		this.manaMax=200+((diff-1)*30);
		this.mana=this.manaMax;
		this.sorts=new ArrayList<Competence> ();
		this.sorts.add(new Abime());
		if(diff>=3)
		{
			this.sorts.add(new Charge());
		}
		
		
	}
	
	

}
