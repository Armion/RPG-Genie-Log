package character;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import competences.Competence;
import competences.Eclair_givre;
import competences.Poison;
import competences.SRituel;

public class Blob extends Ennemi {
	
	public Blob(int diff,String nom) throws SlickException
	{
		this.atk=8+((diff-1)*3);
		this.def=8+((diff-1)*3);
		this.lvl=diff;
		this.pvMax=25+((diff-1)*3);
		this.pv=this.pvMax;
		this.loot=2;
		this.nom="Blob "+nom;
		this.profil=1;
		this.manaMax=150+((diff-1)*30);
		this.mana=this.manaMax;
		this.sorts=new ArrayList<Competence> ();
		this.sorts.add(new Poison());
		
		this.setText("src/Combat/personnages/sprites/slime.png");
	}

}
