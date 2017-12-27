package Combat.personnages;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import Combat.competences.Eclair_givre;
import Combat.competences.Smite;

public class Guerrier extends Joueur {

	 public Guerrier() throws SlickException
	 {
		 
		 this.atk=10;
		 this.def=7;
		 this.lvl=1;
		 this.pvMax=20;
		 this.pv=this.pvMax;
		 this.xp=0;
		 this.nom="Guerrier"; 
		 this.manaMax=75;
		 this.mana=75;
		 
		 this.sorts.add(new Smite());
		 this.sorts.add(new Eclair_givre());
	 }
}