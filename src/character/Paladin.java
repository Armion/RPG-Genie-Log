package character;
import org.newdawn.slick.SlickException;

import competences.*;


public class Paladin extends Joueur {
	
	public Paladin() throws SlickException
	{
		super();
		this.atk=5;
		this.def=10;
		this.pvMax=20;
		this.pv=this.pvMax;
		this.lvl=1;
		this.xp=0;
		this.nom="Paladin";
		this.manaMax=200;
		this.mana=200;
		this.sorts.add(new Smite());
		this.sorts.add(new Heal());
		
	}
	
	

}
