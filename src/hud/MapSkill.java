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
	private Image skillsPicture;
	private List<List<Competence>> liste;
	private GameContainer container;
	
	
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
		
		for(int i = 0; i < liste.size(); i++)
		{
			
			//for(Competence c : liste.get(i))
			for(int t = 0; t < liste.get(i).size() ; t++)
			{
				
				g.drawImage(liste.get(i).get(t).getIcone(), x+36 + i*60, y+71+t*72);
			}
		}
	}
	
	
	
	private void loadSkill()
	{
		this.liste = new ArrayList<>();
		List<Competence> competences = new ArrayList<>();
		
		
		for(Entitee e : Team.getInstance().getTeam())
		{
			competences = new ArrayList<>();
			
			for(Competence c : e.getComp())
			{
				if(c.usableOutOfFight())
				{
					competences.add(c);
				}
			}
			
			this.liste.add(competences);
		}
		
	}
	
	

}
