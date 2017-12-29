package hud;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import character.Entitee;
import competences.Competence;
import singleton.Team;

public class MapSkill extends HUD{
	
	private int x;
	private int y;
	private int page;
	private Image skillsPicture;
	List<Competence> liste;
	
	public void init(GameContainer container) throws SlickException 
	{
		
		this.loadSkill();
		
		
		skillsPicture = new Image("resources/hud/Skill.png");
		this.x = 10;
		this.y = container.getHeight()/2 - this.skillsPicture.getHeight()/2;
		
	}
	
	
	public void render(GameContainer container, Graphics g) {
		g.resetTransform();
		g.drawImage(skillsPicture, x, y);
	}
	
	
	
	private void loadSkill()
	{
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
