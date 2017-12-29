package character;

import java.util.Random;

import org.newdawn.slick.SlickException;

import competences.Heal;
import competences.MultiHeal;
import competences.Renforcement;

public class Pretre extends Joueur{
	
	public Pretre() throws SlickException
	{
		super();
		this.atk=3;
		this.def=10;
		this.pvMax=20;
		this.pv=this.pvMax;
		this.lvl=1;
		this.xp=0;
		this.nom="Pretre";
		this.manaMax=300;
		this.mana= manaMax;
		this.sorts.add(new MultiHeal());
		this.sorts.add(new Heal());
		this.path="src/Combat/personnages/sprites/generique.png";
		
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
