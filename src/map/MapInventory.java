package map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import inventaire.Inventory;
import items.Item;


public class MapInventory implements ComponentListener{
	
	private boolean visible;
	private Image inventairePicture;
	private int x = 0;
	private int y = 100;
	private Inventory inventaire;
	private MouseOverArea[] items;
	

	
	public void init(GameContainer container, Inventory inventaire) throws SlickException {
		
		this.inventaire = inventaire;
		this.items  = new MouseOverArea[this.inventaire.getItemsList().size()];
		
		for(int i = 0; i < this.items.length ; i++)
		{
			this.items[i] = new MouseOverArea(container, inventaire.getItemsList().get(i).getIcone(), (x+11+i*35), y+36*((i/4)+1), this);
		}
		
		this.visible = false;
		this.inventairePicture = new Image("resources/hud/inventaire.png");
	}

	public void render(GameContainer container, Graphics g) {
		g.resetTransform();
		g.drawImage(inventairePicture, x, y);
		
		for(int i = 0; i < this.items.length; i++)
		{
			items[i].render(container, g);
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
		
		for(int i = 0; i < this.items.length; i++)
		{
			if(source == items[i])
			{
				System.out.println(this.inventaire.getItemsList().get(i).getName());
			}
		}
		
	}
	

	

}
