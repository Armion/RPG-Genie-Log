package singleton;

import java.util.List;

import org.newdawn.slick.SlickException;

import character.Entitee;
import character.Paladin;

public class Team {
	
	private List<Entitee> membres;
	private int money;
	
	private Team()
	{
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
	
	public  List<Entitee> getTeam()
	{
		return this.membres;
	}

}