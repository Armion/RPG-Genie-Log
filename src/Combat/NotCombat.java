package Combat;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class NotCombat extends BasicGameState {
	
	Image background;
	private StateBasedGame game;
	public static final int ID=2;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		this.background=new Image("src/background/background.jpg");
		this.game=arg1;
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		this.background.draw(0,0,arg0.getWidth(),arg0.getHeight());

	}
	
	

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		

	}

	@Override
	public int getID() {
		
		return this.ID;
	}
	
	@Override
    public void keyPressed(int key, char c) {
		
		game.enterState(CombatScreen.ID);
       
    }

}
