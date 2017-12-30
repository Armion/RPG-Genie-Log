package singleton;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

import character.Entitee;
import character.Joueur;
import character.Paladin;
import character.Pretre;
import inventaire.Inventory;
import items.Item;
import items.consommables.MedaillonArmion;
import items.consommables.Pain;
import items.consommables.potions.HealPot;
import items.consommables.potions.ManaPot;
import items.equipements.Amulette;

public class Team {
	
	private List<Joueur> membres;
	private int money;
	private Inventory inventaire;
	private int zone;
	
	private Team()
	{
		this.membres = new ArrayList<>();
		try {
			this.membres.add(new Paladin());
			this.membres.add(new Paladin());
			this.membres.add(new Paladin());
			this.membres.add(new Pretre());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.money = 100;
		
		List<Item> listeInventaire = new ArrayList<>();
		
		for(int i = 0 ; i < 20 ; i++)
		{
			listeInventaire.add(new Pain());
			listeInventaire.add(new HealPot(50));
			listeInventaire.add(new HealPot(20));
			listeInventaire.add(new HealPot(30));
			listeInventaire.add(new HealPot(40));
			listeInventaire.add(new HealPot(30));
			listeInventaire.add(new Pain());
			listeInventaire.add(new ManaPot(80));
			listeInventaire.add(new ManaPot(80));
			listeInventaire.add(new ManaPot(80));
			listeInventaire.add(new ManaPot(80));
			listeInventaire.add(new Pain());
			listeInventaire.add(new Pain());
			listeInventaire.add(new ManaPot(80));
		}
		
		listeInventaire.add(new MedaillonArmion());
		listeInventaire.add(new Amulette());

		
		this.inventaire = new Inventory( listeInventaire, 500);
		this.zone=0;
	}
	
	
	
	private static Team INSTANCE = new Team();
	
	public static Team getInstance()
	{
		return INSTANCE;
	}
	
	public  List<Joueur> getTeam()
	{
		return this.membres;
	}
	
	public Inventory getInventory()
	{
		return this.inventaire;
	}
	
	public void virerMembre(Joueur membre)
	{
		membres.remove(membre);
	}
	
	public void addMembre(Joueur membre)
	{
		membres.add(membre);
	}
	
	public void addMoney(int money)
	{
		this.money=this.money+money;
	}
	
	public void removeMoney(int reduce)
	{
		this.money=this.money-reduce;
	}
	
	public int getMoney()
	{
		return this.money;
	}
	public int getZone()
	{
		return this.zone;
	}
	public void setZone(int z)
	{
		this.zone=z;
	}
	
	

}
