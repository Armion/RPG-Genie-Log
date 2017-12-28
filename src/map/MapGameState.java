package map;


//represente l'etat du jeu quand on est dans la map
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Combat.CombatScreen;



public class MapGameState extends BasicGameState {
	//l'ID de l'etat
	public static final int ID = 2;

	//on va avoir besoin d'un container pour afficher la phase, une map, une représentation du joueur sur la map, un controle des events, l'HUD et la camera
	private GameContainer container;
	private Map map = new Map();
	private MapPlayer player = new MapPlayer(map);
	private MapTriggerController triggers = new MapTriggerController(map, player);
	private MapCamera camera = new MapCamera(player);
	private MapPlayerController controller = new MapPlayerController(player);
	private MapHud hud = new MapHud();

	private Music musicBack;

	@Override
	//on charge et initialise si besoin les resources à l'initialisation
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		this.container = container;
		this.musicBack = new Music("resources/sound/lost-in-the-meadows.ogg");
		this.map.init();
		this.player.init();
		this.hud.init();
		this.controller.setInput(container.getInput());
		container.getInput().addKeyListener(controller);
	}

	//methode appellé quand on rentre dans cette phase
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		//on fait boucler la musique de fond
		musicBack.loop();
	}

	//methode appellé quand on quite cette phase
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
		
		//enfin, on peut afficher l'HUD
		this.hud.render(g);
	}

	//methode d'update pour mettre à jour les elements de la phase
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		//on demande aux elements de se mettre à jour
		this.controller.update();
		this.triggers.update();
		this.player.update(delta);
		this.camera.update(container);
		
		//enfin, on a une chance de rentrer en combat, dans ce cas on passe à la phase de combat
		if (Math.random() < 0.010 && player.isMoving()) {
			game.enterState(CombatScreen.ID);
		}
	}

	//on regarde les touches relachées, si c'est un echappe, alors on quitte le jeu
	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			this.container.exit();
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
