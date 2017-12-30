package character;
import java.util.Random;

import org.newdawn.slick.SlickException;

import competences.*;


public class Paladin extends Joueur {
	
	public Paladin() throws SlickException
	{
		super();
		this.atk=10;
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
		this.path="src/Combat/personnages/sprites/paladin.png";
		
	}
	
	
	@Override
	protected void UpgradeComp()
	{
		Random rand=new Random();
		int augAtk=rand.nextInt(2)+2;
		int augDef=rand.nextInt(4)+4;
		int augPV=rand.nextInt(5)+5;
		int augMana=rand.nextInt(20)+5;
		this.atk=this.atk+augAtk;
		this.def=this.def+augDef;
		this.pvMax=this.pvMax+augPV;
		this.manaMax=this.manaMax+augMana;
		if(this.lvl==3)
		{
			this.sorts.add(new Renforcement());
		}
		
		
	}
	
	

}
