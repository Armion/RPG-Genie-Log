package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import map.MapGameState;

//classe qui represente le menu principal
public class MainScreenGameState extends BasicGameState {

	//on va avoir besoin d'un background et de l'etat Principal du jeu
	public static final int ID = 1;
	private Image background;
	private StateBasedGame game;

	//surcharge d'init pour aller chercher les resources
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		this.background = new Image("resources/background/forest.png");
	}

	//la methode qui gere l'affichage pour cette etat
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		//on affiche le fond et un message qui demande d'appuyer sur une touche
		background.draw(0, 0, container.getWidth(), container.getHeight());
		g.drawString("Appuyer sur une touche", 300, 300);
	}

	//methode d'uppdate, il n'y a rien a uppdate dans le menu principal
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}

	
	//gestion des touches relachés, peu importe la touché on va charger la phase de jeu de la Map
	@Override
	public void keyReleased(int key, char c) {
		game.enterState(MapGameState.ID);
	}

	//Un identifiant, il permet d'indentifier l'etat dans le quel on est
	@Override
	public int getID() {
		return ID;
	}
}
