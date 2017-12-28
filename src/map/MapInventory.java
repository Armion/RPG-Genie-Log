package map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class MapInventory {
	
	private boolean visible;
	private Image inventaire;
	private int x = 0;
	private int y = 100;
	
	public void init() throws SlickException {
		
		this.visible = false;
		this.inventaire = new Image("resources/hud/inventaire.png");
	}

	public void render(Graphics g) {
		g.resetTransform();
		g.drawImage(inventaire, x, y);
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
	

	

}
