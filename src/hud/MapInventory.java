package hud;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import inventaire.Inventory;
import items.Item;
import singleton.Team;


public class MapInventory implements ComponentListener{
	
	private boolean visible;
	private Image inventairePicture;
	private Image gold;
	private int x;
	private int y;
	private int page;
	private Inventory inventaire;
	private java.util.Map<MouseOverArea, Item> items;
	private MouseOverArea gauche;
	private MouseOverArea droit;
	private GameContainer container;
	

	
	public void init(GameContainer container, Inventory inventaire) throws SlickException {
		
		this.inventairePicture = new Image("resources/hud/inventaire.png");
		this.gold = new Image("resources/hud/GoldCoin.png");
		this.visible = false;
		this.page =1;
		this.inventaire = inventaire;
		this.container = container;
		
		this.x = container.getWidth() -  this.inventairePicture.getWidth();
		this.y = container.getHeight()/2 - this.inventairePicture.getHeight()/2;
		
		
		
		gauche = new MouseOverArea(container, new Image("resources/hud/invGauche.png"), x+10, y+397, this);
		droit = new MouseOverArea(container, new Image("resources/hud/invDroit.png"), x+50, y+397, this);
		
		this.loadInventory(container);
		
		
		
		
		
		
	}

	public void render(GameContainer container, Graphics g) {
		g.resetTransform();
		g.drawImage(inventairePicture, x, y);
		
		for(Entry<MouseOverArea, Item> entry : items.entrySet()) {
		     entry.getKey().render(container, g);
		}
		
		gauche.render(container, g);
		g.drawString("" + page, x+ 35, y+400);
		droit.render(container, g);
		
		g.drawImage(gold, x+130, y+397);
		g.drawString(Team.getInstance().getMoney() +"", x+160, y+400);
		
	}
	
	public boolean isVisible()
	{
		return this.visible;
	}
	
	public void changeState()
	{
		if(this.visible)
			this.visible = false;
		else
			this.visible = true;
		
		

	}

	@Override
	public void componentActivated(AbstractComponent source) {
		
		
		for(Entry<MouseOverArea, Item> entry : items.entrySet())
		{
			if(source == entry.getKey())
			{
				inventaire.useItem(entry.getValue().getId(), Team.getInstance().getTeam().get(0), "");
				this.loadInventory(this.container);
			}
			
		}
		
		if(source == gauche && page >1)
		{
			page --;
			this.loadInventory(container);
		}
		if(source == droit && page < (inventaire.getItemsList().size()-1)/60+1)
		{
			page ++;
			this.loadInventory(container);
			
		}
		
		
		
	}
	
	
	private void loadInventory(GameContainer container)
	{
		this.items = new HashMap<>();
		//on fabrique le map des boutons avec l'item correspondant
		for(int i = (page -1 )*60; i < page*60 ; i++)
		{	
			if(i< this.inventaire.getItemsList().size())
			this.items.put(new MouseOverArea(container, inventaire.getItemsList().get(i).getIcone(), (x+11+((i%6)%60)*35), y+36*(((i%60)/6)+1), this), inventaire.getItemsList().get(i));
		}
		
		
	}
	

	

}
