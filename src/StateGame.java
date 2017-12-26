import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class StateGame extends StateBasedGame {

	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new StateGame(), 800, 600, false).start();
	}

	public StateGame() {
		super("HELL");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new MainScreenGameState());
		addState(new MapGameState());
	}

}