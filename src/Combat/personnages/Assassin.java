package Combat.personnages;

import org.newdawn.slick.SlickException;

import Combat.competences.Poison;

public class Assassin extends Joueur {
	
	public Assassin() throws SlickException
	{
		super();
		this.atk=15;
		this.def=5;
		this.pvMax=20;
		this.pv=this.pvMax;
		this.lvl=1;
		this.xp=0;
		this.nom="Assassin";
		this.manaMax=100;
		this.mana=100;
		this.sorts.add(new Poison());
		
	}
	
	
	

}
