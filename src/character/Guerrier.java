package character;

import java.util.Random;


import org.newdawn.slick.SlickException;

import competences.Charge;
import competences.Saignement;

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
		 this.path="src/Combat/personnages/sprites/generique.png";
		 
		 
		 this.sorts.add(new Charge());
	 }
	 
	 @Override
		protected void UpgradeComp()
		{
			Random rand=new Random();
			int augAtk=rand.nextInt(3)+3;
			int augDef=rand.nextInt(3)+3;
			int augPV=rand.nextInt(5)+5;
			int augMana=rand.nextInt(5)+1;
			this.atk=this.atk+augAtk;
			this.def=this.def+augDef;
			this.pvMax=this.pvMax+augPV;
			this.manaMax=this.manaMax+augMana;
			if(this.lvl==3)
			{
				this.sorts.add(new Saignement());
			}
			
			
		}
		
	 
}