package map;

import java.util.HashMap;
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
	private int x = 0;
	private int y = 100;
	private Inventory inventaire;
	private java.util.Map<MouseOverArea, Item> items;
	

	
	public void init(GameContainer container, Inventory inventaire) throws SlickException {
		
		this.inventaire = inventaire;
		
		this.items = new HashMap<>();
		//on fabrique le map des boutons avec l'item correspondant
		for(int i = 0; i < inventaire.getItemsList().size() ; i++)
		{
			this.items.put(new MouseOverArea(container, inventaire.getItemsList().get(i).getIcone(), (x+11+i*35), y+36*((i/4)+1), this), inventaire.getItemsList().get(i));
		}
		
		
		
		
		this.visible = false;
		this.inventairePicture = new Image("resources/hud/inventaire.png");
	}

	public void render(GameContainer container, Graphics g) {
		g.resetTransform();
		g.drawImage(inventairePicture, x, y);
		
		for(Entry<MouseOverArea, Item> entry : items.entrySet()) {
		     entry.getKey().render(container, g);
		}
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
				System.out.println(entry.getValue().getName());
			}
			
		}
		
		
	}
	

	

}
