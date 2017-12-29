package items.equipements;

import character.Entitee;
import character.Joueur;
import items.Item;

public abstract class Equipement extends Item {
	
	protected int type; //1= armure 2=arme1main 3= arme2mains 
	protected int bolusDeg;
	protected int bolusDef;
	protected int bolusPV;
	protected int bolusMana;
	protected boolean equiped;
	
	
	public Equipement()
	{
		super();
		this.fight=false;
		this.stacks=-2;
		this.equiped=false;
		this.bolusDef=0;
		this.bolusDeg=0;
		this.bolusPV=0;
		this.bolusMana=0;
		
	}
	
	public int getBDeg()
	{
		return this.bolusDeg;
	}
	
	public int getBDef()
	{
		return this.bolusDef;
	}
	public int getBPV()
	{
		return this.bolusPV;
	}
	
	public int getBMana()
	{
		return this.bolusMana;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public boolean isEquipe()
	{
		return this.equiped;
	}
	public void setEquiped(boolean equip)
	{
		this.equiped=equip;
	}
	
	public String Equiper(Entitee cible)
	{
		return cible.EquiperItem(this);
		
	}
	
	public void Desequiper(Entitee cible)
	{
		cible.DesequiperItem(this);
	}
	
	@Override
	public void utiliser(String log, Entitee cible)
	{
		if (this.equiped==false)
		{
			Equiper(cible);
		}
		else
			
		{
			Desequiper(cible);
		}
		
	}
	

}
