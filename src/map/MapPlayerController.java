package map;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

//methode pour controler le joueur et ses mouvements

public class MapPlayerController implements KeyListener {

	private MapPlayer player;
	private Input input;

	public MapPlayerController(MapPlayer player) {
		this.player = player;
	}

	//methode d'update, elle est utile lorsque qu'une manette est connecté, sinon c'est le KeyListener qui s'en charge
	public void update() {
		/*if (input.getControllerCount() > 0) {
			player.setDx(input.getAxisValue(0, 1));
			player.setDy(input.getAxisValue(0, 2));
		}*/
	}

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

	//on met le vecteur à jour en fonction de la touche préssée
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

}
