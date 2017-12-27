package character;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import competences.Competence;
import competences.Eclair_givre;

public class DarkKnight extends Ennemi {
	
	
	
	public DarkKnight(int diff,int nom) throws SlickException
	{
		this.atk=15+((diff-1)*4);
		this.def=10+((diff-1)*4);
		this.lvl=diff;
		this.pvMax=40+((diff-1)*4);
		this.pv=this.pvMax;
		this.loot=3;
		this.nom="Chevalier Noir "+nom;
		this.profil=1;
		this.manaMax=100+((diff-1)*4);
		this.mana=100+((diff-1)*4);
		this.sorts=new ArrayList<Competence> ();
		this.sorts.add(new Eclair_givre());
		
		this.setText("src/Combat/personnages/sprites/chevalier.png");
	}
	
	public static int getLoot()
	{
		return 1;
	}
	
	
	

}
