package hud;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import character.Joueur;
import main.Couple;
import map.MapGameState;
import singleton.Team;

public class SelectionTeam extends BasicGameState implements ComponentListener{
	
	private int x;
	private int y;
	private Image selectionImage;
	private List<Couple<Joueur, MouseOverArea>> liste;
	private Joueur selection = null;
	public static final int ID = 4;
	private Image hold;
	private StateBasedGame game;
	private String type;
	private UUID item;


	
	
	private void loadPlayers(GameContainer container)
	{
		this.liste = new ArrayList<>();
		int x = this.x +26;
		
		for(Joueur j : Team.getInstance().getTeam())
		{
			liste.add(new Couple<>(j, new MouseOverArea(container, j.getPortrait(), x, this.y+60, this)));
			
			x+= 61;
		}
	}
	


	@Override
	public void componentActivated(AbstractComponent source) {
		// TODO Auto-generated method stub
		
		for(Couple<Joueur, MouseOverArea> c : liste)
		{
			if(source == c.getValue())
			{
				this.selection = c.getKey();
				game.enterState(MapGameState.ID);
			}
		}
		
		
	}


	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		
		try {
			this.selectionImage = new Image("resources/hud/selection.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.type = "";
		x = container.getWidth()/2 - selectionImage.getWidth()/2;
		y = container.getHeight()/2 - selectionImage.getWidth()/2;
		
		hold = new Image(container.getWidth(), container.getHeight());
		
		this.loadPlayers(container);
		
		this.selection = Team.getInstance().getTeam().get(0);
		this.game = game;
		
		
		
	}


	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		

		g.drawImage(hold, 0, 0);
		
		g.drawImage(selectionImage, x, y);
		
		
		for(Couple<Joueur, MouseOverArea> c : liste)
		{
			c.getValue().render(container, g);
		}
		
		
		
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	    
		container.getGraphics().copyArea(hold, 0, 0);
	   }
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		
		if(this.type.equals("competence"))
			selection.subirComp(((MapGameState)game.getState(MapGameState.ID)).getmapSkill().getCompetence());
		else
		{
			if(this.type.equals("item"))
			{
				Team.getInstance().getInventory().useItem(item, selection, "");
				((MapGameState) game.getState(MapGameState.ID)).getInventory().update();
			}
		}
	}
	

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}
	
	public Joueur getSelection()
	{
		return this.selection;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setItem(UUID id)
	{
		this.item = id;
	}

}
