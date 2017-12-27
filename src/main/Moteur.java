
package main;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import battle.BattleGameState;
import map.MapGameState;
import Combat.CombatScreen;
import Combat.personnages.*;

//classe principal du jeu
public class Moteur extends StateBasedGame {
	ArrayList<Joueur> groupe= new ArrayList<Joueur>();
	
	

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
		this.groupe.add(new Guerrier());
		this.groupe.add(new Paladin());
		this.groupe.add(new Assassin());
		//ajout de l'etat menu princpale
		addState(new MainScreenGameState());
		
		//ajout de l'etat de la map du monde
		addState(new MapGameState());
		
		//ajout de l'etat des batailles
		addState(new CombatScreen(this.groupe));
	}

}
