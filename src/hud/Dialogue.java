package hud;

import java.util.UUID;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.StateBasedGame;

import character.pnj.PNJ;
import singleton.ListPNJ;

public class Dialogue extends HUD  implements ComponentListener{

	private Image dialImage;
	private StateBasedGame game;
	private PNJ locuteur;
	
	
	
	public void init(GameContainer container,StateBasedGame game) throws SlickException {

		
		try {
			this.dialImage = new Image("resources/hud/dialogue.png");
		} catch (SlickException e) {

			e.printStackTrace();
		}
		this.game = game;
		this.x = container.getWidth()/2 - dialImage.getWidth()/2;
		this.y = container.getHeight() - 50 - dialImage.getHeight();
		
		
		
		
		
	}
	
	
	public void render(GameContainer container, Graphics g) {
		g.resetTransform();
		g.drawImage(dialImage, x, y);
		g.setColor(new Color(255,255,255));
		String msg = "";
		int i = 0;
		
		
		for(char c : locuteur.getDialogue().toCharArray())
		{
			msg += c;
			if(msg.length() == 24)
				{
					g.drawString(msg, x+13, y+20 + 15*i);
					i++;
					msg = "";
				}
				
		}
		g.drawString(msg, x+13, y+20 + 15*i);
		
	}
	
	@Override
	public void componentActivated(AbstractComponent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void changerLocuteur(UUID id)
	{
		this.locuteur = ListPNJ.getInstance().getPNJ(id);
	}

}
