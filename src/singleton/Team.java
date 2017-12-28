package singleton;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

import character.Entitee;
import character.Joueur;
import character.Paladin;

public class Team {
	
	private List<Joueur> membres;
	private int money;
	
	private Team()
	{
		this.membres = new ArrayList<>();
		try {
			this.membres.add(new Paladin());
			this.membres.add(new Paladin());
			this.membres.add(new Paladin());
			this.membres.add(new Paladin());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.money = 100;
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
	
	public void virerMembre(Joueur membre)
	{
		membres.remove(membre);
	}
	
	public void addMembre(Joueur membre)
	{
		membres.add(membre);
	}

}
