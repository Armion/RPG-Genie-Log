package character;

import java.util.Random;

import org.newdawn.slick.SlickException;

import competences.Fumigene;
import competences.Poison;

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
		this.path="src/Combat/personnages/sprites/generique.png";
		
	}
	
	
	@Override
	protected void UpgradeComp()
	{
		Random rand=new Random();
		int augAtk=rand.nextInt(4)+3;
		int augDef=rand.nextInt(2)+1;
		int augPV=rand.nextInt(5)+1;
		int augMana=rand.nextInt(10)+1;
		this.atk=this.atk+augAtk;
		this.def=this.def+augDef;
		this.pvMax=this.pvMax+augPV;
		this.manaMax=this.manaMax+augMana;
		if(this.lvl==3)
		{
			this.sorts.add(new Fumigene());
		}
		
		
	}
	

}
