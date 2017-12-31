
package main;



import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import map.MapGameState;
import Combat.CombatScreen;
import hud.SelectionTeam;


//classe principal du jeu
public class Moteur extends StateBasedGame {

	
	

	//la methode main
	public static void main(String[] args) throws SlickException 
	{
		
		//on Instancie la classe BattleGame
		AppGameContainer app = new AppGameContainer(new Moteur(), 1000, 800, false);
		//on active le Vsync
		app.setVSync(true);
		//on affiche les FPS
		app.setShowFPS(true);
		//on peut lancer l'application
		app.start();
		
	}

	//constructeur
	public Moteur() {
		super("RPG-Genie-Log");
	}

	//on fait appelle aux sous parties du jeu
	@Override
	public void initStatesList(GameContainer container) throws SlickException 
	{

		//ajout de l'etat menu princpale
		addState(new MainScreenGameState());
		
		//ajout de l'etat de la map du monde
		addState(new MapGameState());
		
		//ajout de l'etat des batailles
		addState(new CombatScreen());
		//ajout de l'état de selection
		addState(new SelectionTeam());
		
		addState(new EndGameState());
		
		
	}

}
