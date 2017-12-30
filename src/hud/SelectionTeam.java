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

import character.Joueur;
import main.Couple;
import singleton.Team;

public class SelectionTeam extends HUD implements ComponentListener{
	
	private int x;
	private int y;
	private Image selectionImage;
	private List<Couple<Joueur, MouseOverArea>> liste;
	private GameContainer container;
	private Joueur selection = null;
	
	private static SelectionTeam INSTANCE;
	
	private SelectionTeam(GameContainer container)
	{
		this.loadPlayers(container);
		x = 500;
		y = 400;
		
		try {
			this.selectionImage = new Image("resources/hud/selection.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static SelectionTeam getInstance(GameContainer container)
	{
		if(INSTANCE == null)
			INSTANCE = new SelectionTeam(container);
		
		return INSTANCE;
	}
	
	public void render(GameContainer container, Graphics g) {
		
		g.drawImage(selectionImage, x, y);
		
		//horrible mais ça fonctionne au moin
		this.loadPlayers(container);
		
		for(Couple<Joueur, MouseOverArea> c : liste)
		{
			c.getValue().render(container, g);
		}
	}
	
	
	
	public Joueur selectionner()
	{
		this.changeState();
		return selection;
	}
	
	
	private void loadPlayers(GameContainer container)
	{
		this.liste = new ArrayList<>();
		int x = this.x +26;
		
		for(Joueur j : Team.getInstance().getTeam())
		{
			liste.add(new Couple<>(j, new MouseOverArea(container, j.getPortrait(), x, y+60, this)));
			
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
				selection = c.getKey();
				System.out.println(c.getKey().getNom());
			}
		}
		
		
	}

}
