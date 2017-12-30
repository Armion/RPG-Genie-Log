package hud;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

import competences.Competence;
import main.Couple;
import singleton.Team;
import character.Joueur;

public class MapSkill extends HUD implements ComponentListener{
	
	private Image skillsPicture;
	private List<Couple<Joueur, List<Couple<Competence, MouseOverArea>>>> liste;
	private GameContainer container;
	private StateBasedGame game;
	private Competence competence;

	
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		this.game = game;
		this.container = container;

		skillsPicture = new Image("resources/hud/Skill.png");
		this.x = 10;
		this.y = container.getHeight()/2 - this.skillsPicture.getHeight()/2;
		
		this.loadSkill(container);
		
		
	}
	
	
	public void render(GameContainer container, Graphics g) {
		g.resetTransform();
		g.drawImage(skillsPicture, x, y);
		
		for(Couple<Joueur, List<Couple<Competence, MouseOverArea>>> l : liste)
		{
			for(Couple<Competence, MouseOverArea> c : l.getValue())
			{
				c.getValue().render(container, g);
			}
			
		}
	}
	
	
	
	private void loadSkill(GameContainer container)
	{
		List<Couple<Competence, MouseOverArea>> competences = new ArrayList<>();
		this.liste = new ArrayList<>();
		
		int i = 0;
		int t = 0;

		
		for(Joueur e : Team.getInstance().getTeam())
		{
			competences = new ArrayList<>();
			
			
			t = 0;
			for(Competence c : e.getComp())
			{
				if(c.usableOutOfFight())
				{
					competences.add(new Couple<>(c, new MouseOverArea(container, c.getIcone(), x+36 + i*60, y+71 +t*72, this)));
					t++;
				}
				
			}
			
			this.liste.add(new Couple<>(e, competences));
			
			i++;
		}
		
	}


	public void update(StateBasedGame game)
	{
	
	}
	
	public Competence getCompetence()
	{
		return this.competence;
	}
	
	
	
	@Override
	public void componentActivated(AbstractComponent source) {
		
		for(Couple<Joueur, List<Couple<Competence, MouseOverArea>>> l : liste)
		{
			for(Couple<Competence, MouseOverArea> c : l.getValue())
			{
				if(source == c.getValue())
				{
					game.enterState(SelectionTeam.ID);
					l.getKey().reduireMana(c.getKey().getCout());
					competence = c.getKey();
					((SelectionTeam) game.getState(SelectionTeam.ID)).setType("competence");
					
				}
			}
			
		}
		
	}
	
	

}
