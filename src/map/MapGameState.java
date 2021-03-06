package map;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import org.newdawn.slick.Color;
//represente l'etat du jeu quand on est dans la map
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Combat.CombatScreen;
import character.PNJ;
import hud.Dialogue;
import hud.HUD;
import hud.MapInventory;
import hud.MapSkill;
import main.EndGameState;
import main.MainScreenGameState;
import singleton.ListPNJ;
import singleton.Team;
import singleton.log.LigneLog;
import singleton.log.Logs;



public class MapGameState extends BasicGameState implements Observer{
	//l'ID de l'etat
	public static final int ID = 2;

	//on va avoir besoin d'un container pour afficher la phase, une map, une repr�sentation du joueur sur la map, un controle des events, l'HUD et la camera
	private GameContainer container;
	private Map map = new Map();
	private MapPlayer player = new MapPlayer(map);
	private Team team = Team.getInstance();
	private MapInventory inventaire = new MapInventory();
	private MapTriggerController triggers;
	private MapCamera camera = new MapCamera(player);
	private MapPlayerController controller;;
	private String log = "";
	private List<LigneLog> logs;
	private StateBasedGame jeu;
	private MapSkill skills = new MapSkill();
	private Dialogue dialogue = new Dialogue();
	Stack<HUD> fenetres;
	private StateBasedGame game;
	
	

	private Music musicBack;

	@Override
	//on charge et initialise si besoin les resources � l'initialisation
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		this.container = container;
		this.game =game;

		this.map.init();
		this.triggers = new MapTriggerController(map, player);
		this.controller = new MapPlayerController(player, container.getGraphics());

		
		this.inventaire.init(container, game);
		this.skills.init(container, game);

		this.musicBack = new Music("resources/sound/lost-in-the-meadows.ogg");

		this.player.init();
		this.controller.setInput(container.getInput());
		container.getInput().addKeyListener(controller);
		Logs.getInstance().addObserver(this);
		this.logs = new ArrayList<>();
		this.fenetres = new Stack<>();
		
		this.dialogue.init(container, game);
		
		
		this.jeu = game;
	}

	//methode appell� quand on rentre dans cette phase
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		//on fait boucler la musique de fond
		musicBack.loop();
	}

	//methode appell� quand on quite cette phase
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		//on stop la musique
		musicBack.stop();
	}

	//methode pour faire le rendu de cette phase de jeu
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
	{
		//on place bien la camera
		this.camera.place(container, g);
		
		//on affiche la partie de la map sous le joueur
		this.map.renderBackground();
		
		//on met le joueur par dessus
		this.player.render(g);
		
		//on remet ce qui doit etre par dessus le joueur
		this.map.renderForeground();
		
		//si l'inventaire doit l'etre, on l'affiche
		if(inventaire.isVisible())
			inventaire.render(container, g);
		
		//si l'inventaire doit l'etre, on l'affiche
		if(skills.isVisible())
			this.skills.render(container, g);
		
		if(dialogue.isVisible())
			this.dialogue.render(container, g);
		
		
		//on affiche les logs
		this.writeLogs(g);
		
	}

	//methode d'update pour mettre � jour les elements de la phase
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		//on demande aux elements de se mettre � jour
		this.controller.update();
		this.triggers.update();
		this.player.update(delta);
		this.camera.update(container);
		
		//si on se deplace et qu'on parlait � un PNJ, alors on lui parle plus
		if(this.player.isMoving() && this.dialogue.isVisible())
			this.fermerFenetre(dialogue);
		
		//enfin, on a une chance de rentrer en combat, dans ce cas on passe � la phase de combat
		if(Team.getInstance().getZone()==0)
		{
		if (Math.random() < 0.000 && player.isMoving()) {
			game.enterState(CombatScreen.ID);
		}
		}
		else
		{
			if (Math.random() < 0.005 && player.isMoving()) {
				game.enterState(CombatScreen.ID);
			}
			
		}
		
	}
	
	

	//on regarde les touches relach�es, pour afficher les HUD ou retourner � l'ancienne state
	@Override
	public void keyReleased(int key, char c) {
		
		
		switch(key)
		{
		case Input.KEY_ESCAPE : //on regarde qu'aucun HUD n'est ouvert sinon on le ferme, le cas contraire ou retourne au Main Screen
			{
				if(this.fenetres.isEmpty())
				{
					jeu.enterState(MainScreenGameState.ID);
				}
				else
				{
					fenetres.pop().changeState();
				}
				
				
				break;
			}
			
		case Input.KEY_I : //si on appuis sur i on ouvre l'inventaire
			{
				if(! this.inventaire.isVisible())
				{
					this.ouvrirFenetre(this.inventaire);
				}
				else
				{
					this.fermerFenetre(this.inventaire);
				}
				break;
			}
		case Input.KEY_P : //p sert pour les skills
			{
				if(! this.skills.isVisible())
				{
					this.ouvrirFenetre(this.skills);
				}
				else
				{
					this.fermerFenetre(this.skills);
				}
			break;
			}
		case Input.KEY_ENTER :  //comme il n'y a pas des masses de PNJ on verifi directement ici, le PNJ coffre, sinan on ouvre le bon dialogue
		{
			PNJ msg = this.controller.trigPNJ();
			if(msg != null)
			{
				if(msg.getNom().equals("Coffre"))
				{
					this.game.enterState(EndGameState.ID);
				}
				
				if(! this.dialogue.isVisible())
				{
					this.dialogue.changerLocuteur(msg.getid());
					this.ouvrirFenetre(dialogue);
				}
					
				else
				{
					this.fermerFenetre(dialogue);
				}
					
				
			}
				
		break;
		}
			
		}
		
	}

	@Override
	public int getID() {
		return ID;
	}

	//methode quand l'unique objet observ� est mit � jour
	@Override
	public void update(Observable observable, Object objectConcerne) {
		
		if(observable.equals(Logs.getInstance()))
		{
			this.logs.add(Logs.getInstance().getLatestLog());
		}
		
	}
	
	//methode pour afficher es logs
	public void writeLogs(Graphics g)
	{
		//on change la couleur
		g.setColor(new Color(255,255,255));
		
		//on regarde l'heure actuelle
		Date d = new Date();
		
		//on instanci une ligne de logs
		LigneLog ligne;
		
		//on parcourt tout les logs � la recherche d'un log qui ne doit plus etre affich� et retirer de la liste
		for(Iterator<LigneLog> it = logs.iterator() ; it.hasNext();)
		{
			ligne = it.next();
			if(d.getTime() - ligne.getDate().getTime() > 2000)
			{
				it.remove();
			}
			
		}
		
		
		log = "";
		//on creer un string qui va contenir tout les logs � raison de 1 par ligne
		for(LigneLog e : logs)
		{
			log += "\n" + e.getContent();
		}
		//on peut enfin afficher le string des logs � l'�cran
		g.drawString(log, container.getWidth()/2-100, container.getHeight()/2);
		
	}
	
	//methode qui tente de passer un HUD en visible
	private void ouvrirFenetre(HUD win)
	{
		if(!win.isVisible())
		{
			win.changeState();
			this.fenetres.push(win);
		}
	}
	
	//methode qui tente de cacher un HUD
	private void fermerFenetre(HUD win)
	{
		//on prepare une pile temporaire pour aller chercher le bon HUD sans perdre ceux au dessus
		Stack<HUD> temp = new Stack<>();
		HUD it = null;
		
		//recherche
		while(! this.fenetres.isEmpty() &&  ! (it = this.fenetres.pop()).equals(win))
		{
			temp.push(it);
		}
		
		//mise � jour si on a trouv� le bon HUD
		if(it != null)
		{
			it.changeState();
		}
		
		//on rempile les HUD qui etaient au dessus
		while(! temp.isEmpty())
		{
			this.fenetres.push(temp.pop());
		}
		
	}
	
	public MapSkill getmapSkill()
	{
		return this.skills;
	}
	
	public MapInventory getInventory()
	{
		return this.inventaire;
	}
	

}
