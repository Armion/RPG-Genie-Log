package map;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

//represente l'etat du jeu quand on est dans la map
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Combat.CombatScreen;
import singleton.Logs;
import singleton.Team;



public class MapGameState extends BasicGameState implements Observer{
	//l'ID de l'etat
	public static final int ID = 2;

	//on va avoir besoin d'un container pour afficher la phase, une map, une repr�sentation du joueur sur la map, un controle des events, l'HUD et la camera
	private GameContainer container;
	private Map map = new Map();
	private MapPlayer player = new MapPlayer(map);
	private Team team = Team.getInstance();
	private MapInventory inventaire = new MapInventory();
	private MapTriggerController triggers = new MapTriggerController(map, player);
	private MapCamera camera = new MapCamera(player);
	private MapPlayerController controller = new MapPlayerController(player);
	
	

	private Music musicBack;

	@Override
	//on charge et initialise si besoin les resources � l'initialisation
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		this.inventaire.init(container, this.team.getInventory());
		this.container = container;
		this.musicBack = new Music("resources/sound/lost-in-the-meadows.ogg");
		this.map.init();
		this.player.init();
		this.controller.setInput(container.getInput());
		container.getInput().addKeyListener(controller);
		Logs.getInstance().addObserver(this);
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
		
	}

	//methode d'update pour mettre � jour les elements de la phase
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		//on demande aux elements de se mettre � jour
		this.controller.update();
		this.triggers.update();
		this.player.update(delta);
		this.camera.update(container);
		
		//enfin, on a une chance de rentrer en combat, dans ce cas on passe � la phase de combat
		if (Math.random() < 0.010 && player.isMoving()) {
			game.enterState(CombatScreen.ID);
		}
	}

	//on regarde les touches relach�es, si c'est un echappe, alors on quitte le jeu
	@Override
	public void keyReleased(int key, char c) {
		
		switch(key)
		{
		case Input.KEY_ESCAPE : this.container.exit();
			break;
		case Input.KEY_I : inventaire.changeState();
		}
		
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void update(Observable observable, Object objectConcerne) {
		
		if(observable.equals(Logs.getInstance()))
		{
			List<String> logs = Logs.getInstance().getLastLogs(Logs.getInstance().getNbLogs());
			for(int i = 0; i < logs.size(); i++)
			{
				System.out.println(logs.get(i));
			}
		}
		
	}

}
