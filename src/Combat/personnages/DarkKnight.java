package Combat.personnages;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

import Combat.competences.Competence;
import Combat.competences.Eclair_givre;

public class DarkKnight extends Ennemi {
	
	
	
	public DarkKnight(int diff,int nom) throws SlickException
	{
		this.atk=15;
		this.def=10;
		this.lvl=diff;
		this.pvMax=40;
		this.pv=40;
		this.loot=1;
		this.nom="Chevalier Noir "+nom;
		this.profil=1;
		this.manaMax=100;
		this.mana=100;
		this.sorts=new ArrayList<Competence> ();
		this.sorts.add(new Eclair_givre());
		
		this.setText("src/personnages/sprites/chevalier.png");
	}
	
	public static int getLoot()
	{
		return 1;
	}
	
	
	

}
