package map;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import character.PNJ;
import singleton.ListPNJ;


//methode pour controler le joueur et ses mouvements

public class MapPlayerController implements KeyListener {

	private MapPlayer player;
	private Input input;
	private Graphics g;


	public MapPlayerController(MapPlayer player, Graphics g) {
		this.player = player;
		this.g = g;

	}
	

	//d�sactiv� � cause d'un bug chez l'un d'entre nous
	//methode d'update, elle est utile lorsque qu'une manette est connect�, sinon c'est le KeyListener qui s'en charge
	public void update() {
		/*if (input.getControllerCount() > 0) {
			player.setDx(input.getAxisValue(0, 1));
			player.setDy(input.getAxisValue(0, 2));
		}*/
	}

	//on regle la classe pour r�cuperer les entr�es au clavier
	@Override
	public void setInput(Input input) {
		this.input = input;
	}

	@Override
	public boolean isAcceptingInput() {
		return true;
	}

	@Override
	public void inputEnded() {

	}

	@Override
	public void inputStarted() {

	}

	//on met le vecteur � jour en fonction de la touche pr�ss�e
	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			this.player.setDy(-1);
			break;
		case Input.KEY_LEFT:
			this.player.setDx(-1);
			break;
		case Input.KEY_DOWN:
			this.player.setDy(1);
			break;
		case Input.KEY_RIGHT:
			this.player.setDx(1);
			break;
		}
	}

	//de meme lors du relachement
	@Override
	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
		case Input.KEY_DOWN:
			this.player.setDy(0);
			break;
		case Input.KEY_LEFT:
		case Input.KEY_RIGHT:
			this.player.setDx(0);
			break;
		}
		
	}
	
	
	//c'est ici que l'ont regarde si l'ont parle � un PNJ
	public PNJ trigPNJ()
	{
		//distance maximum � la quelle on peut parler � un PNJ
		int distance = 80;
		
		//pour tout les PNJ charg�s dans le singleton, donc les PNJs de la map
		for(PNJ p : ListPNJ.getInstance().getListe())
		{
			//on regarde si le joueur regarde dans sa direction et se tient devant dans la limite de distance
			switch (this.player.getDirection())
			{
			case 0 :
				{
					if(this.player.getX() - p.getPosX() < 33
							&& this.player.getX() - p.getPosX() > -33
							&& this.player.getY() - p.getPosY() <= distance
							&& this.player.getY() - p.getPosY() >= 0)
						return p;
					break;
				}
			case 1 :
				{
					if(this.player.getX() - p.getPosX() < distance
							&& this.player.getX() - p.getPosX() > 0
							&& this.player.getY() - p.getPosY() <= 33
							&& this.player.getY() - p.getPosY() >= -33)
						return p;
					break;
				}
			case 2 :
				{
					if(this.player.getX() - p.getPosX() < 33
							&& this.player.getX() - p.getPosX() > -33
							&& this.player.getY() - p.getPosY() <= 0
							&& this.player.getY() - p.getPosY() >= -distance)
						return p;
					break;
				}
			case 3 :
				{
					if(this.player.getX() - p.getPosX() < 0
							&& this.player.getX() - p.getPosX() > -distance
							&& this.player.getY() - p.getPosY() <= 33
							&& this.player.getY() - p.getPosY() >= -33)
						return p;
					break;
				}
			}
		}
		
		
		return null;
	}

}
