package battle;


import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.state.StateBasedGame;

import map.MapGameState;
import shionn.slick.animation.AnimationListener;

import character.*;

//classe pour gerer les combats
public class BattleController implements InputProviderListener {

	private StateBasedGame game;

	private BattleCharacter player;
	
	
	private BattleEnnemy ennemy;
	private Guerrier ennemi;


	private BattleCommand mode = BattleCommand.NONE;

	private BattleHud hud;

	private Music victory;

	//constructeur qui charge le joueur, les ennemis ainsi que la phase de jeu pour le combat
	public BattleController(BattleCharacter player, BattleEnnemy ennemy, StateBasedGame game)
			throws SlickException {
		this.player = player;
		this.ennemy = ennemy;
		this.ennemi = new Guerrier();
		this.game = game;
		this.victory = new Music("resources/sound/lively-meadow-victory-fanfare.ogg");
		initAnimationListeners();
	}

	//on prepare les animationListeners de l'extension slick Util
	private void initAnimationListeners() {
		//declaration
		AnimationListener playerAssignDamage = new AnimationListener() {
			@Override
			public void on() {
				playerAsignDamage();
			}
		};
		AnimationListener endPlayerAttack = new AnimationListener() {
			@Override
			public void on() {
				endPlayerAttack();
			}
		};
		AnimationListener ennemiAssignDamage = new AnimationListener() {
			@Override
			public void on() {
				ennemyAsignDamage();
			}

		};
		AnimationListener endEnnemiAttack = new AnimationListener() {
			@Override
			public void on() {
				endEnnemyAttack();
			}

		};

		//attribution
		this.player.addAnimationListener(playerAssignDamage, endPlayerAttack);
		this.ennemy.addAnimationListener(ennemiAssignDamage, endEnnemiAttack);
	}

	@Override
	//si c'est bon on demarre la bataille (none est renvoy� apres que l'ennemi ait tapp�)
	public void controlPressed(Command command) {
		if (mode == BattleCommand.NONE) {
			this.mode = (BattleCommand) command;
			startBattle();
		}
	}

	/**
	 * Si le joueur attack il a l'initiative, dans tous les autres cas l'ennemi frappe avant.
	 */
	private void startBattle() {
		switch (mode) {
		case ATTACK:
			player.startAttack();
			break;
		case DEFEND:
		case FLEE:
		default:
			ennemy.startAttack();
			break;
		}
	}

	//calcule des degats
	private void playerAsignDamage() {
		
		hud.addLog("Vous attaquez et infligez " + player.getAttack() + " dégats.");
		player.attaquer(ennemi);
	}

	//quand un joueur fini de tapper, on check l'etat du combat
	private void endPlayerAttack() {
		//si l'ennemie est mort
		if (! ennemi.isAlive()) 
		{
			hud.addLog("Vous avez gagn� !");
			victory.play();
			quitBattle(MapGameState.ID);
		}
		else 
		{
			//sinon on passe dans le bon mode
			switch (mode) {
			// si le joueur attaquait, contre attaque de l'ennemi
			case ATTACK:
				ennemy.startAttack();
				break;
			// pas d'autre cas possible
			default:
				mode = BattleCommand.NONE;
				break;
			}
		}
	}

	//calcul des degats de l'ennemi sur le joueur
	private void ennemyAsignDamage() {
		
		hud.addLog("Vous encaissez une attaque et recevez " + ennemi.getAttack() + " d�gats.");
		ennemi.attaquer(player.getCible());
	}

	//quand l'ennemi a fini, on doit aussi check l'etat du combat
	private void endEnnemyAttack() {
		
		if (!player.isAlive()) {
			hud.addLog("Vous avez perdu !");
			quitBattle(MapGameState.ID);
		} else {
			switch (mode) {
			case DEFEND:
				// si le joueur defend, contre attaque du joueur
				player.startAttack();
				break;
			case FLEE:
				hud.addLog("Vous Fuiyez !");
				quitBattle(MapGameState.ID);
				break;
			default:
				mode = BattleCommand.NONE;
				break;
			}
		}
	}

	//methode pour quitter la bataille
	public void quitBattle(final int nextState) {
		// création d'une pause dans un autre thread pour ne pas bloquer l'affichage
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				mode = BattleCommand.NONE;
				game.enterState(nextState);
			}
		}).start();
	}

	@Override
	public void controlReleased(Command command) {
	}

	public void setHud(BattleHud hud) {
		this.hud = hud;
	}

}
