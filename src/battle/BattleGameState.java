package battle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

//classe qui représente le jeu dans un etat de bataille
public class BattleGameState extends BasicGameState {

	public static final int ID = 3;
	private Image background;
	private BattleEnnemy ennemy = new BattleEnnemy();
	private BattlePlayer player = new BattlePlayer();
	private BattleHud hud;
	private Music music;

	//methode pour preparer le combat, on attribut les touches aux commandes et prepare l'HUD ainsi que le listener
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.background = new Image("resources/background/battle.png");
		this.ennemy.init();
		this.player.init();

		InputProvider provider = new InputProvider(container.getInput());
		provider.bindCommand(new KeyControl(Input.KEY_A), BattleCommand.ATTACK);
		provider.bindCommand(new KeyControl(Input.KEY_D), BattleCommand.DEFEND);
		provider.bindCommand(new KeyControl(Input.KEY_F), BattleCommand.FLEE);
		BattleController controller = new BattleController(player, ennemy, game);
		provider.addListener(controller);
		this.hud = new BattleHud(controller);
		this.hud.init(container);
		this.music = new Music("resources/sound/the-last-encounter-short-loop.ogg");
	}

	//quand on entre dans la bataille, on boucle la musique et reset l'ennemi
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		this.music.loop(1, .2f);
		this.ennemy.reset();
	}

	//quand on quitte la bataille on stop la musique
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		this.music.stop();
	}

	//methode d'affichage, on affiche le fond, le joueur, l'ennemi et l'HUD
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		this.background.draw(0, 0, container.getWidth(), container.getHeight());
		this.player.render(container, g);
		this.ennemy.render(container, g);
		this.hud.render(container, g);
	}

	//methode d'update de la bataille, on va update les combattants
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		this.player.update(delta);
		this.ennemy.update(delta);
	}

	@Override
	public int getID() {
		return ID;
	}
}
