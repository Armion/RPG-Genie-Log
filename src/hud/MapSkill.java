package hud;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import character.Entitee;
import competences.Competence;
import singleton.Team;

public class MapSkill {
	
	private int x;
	private int y;
	private int page;
	List<Competence> liste;
	
	public void init(GameContainer container) throws SlickException {
		
		List<Competence> liste = new ArrayList<>();
		
		for(Entitee e : Team.getInstance().getTeam())
		{
			
			
			for(Competence c : e.getComp())
			{
				if(c.usableOutOfFight())
				{
					liste.add(c);
				}
			}
		}
		
	
	}

}
