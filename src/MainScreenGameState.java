import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainScreenGameState extends BasicGameState {

  public static final int ID = 1;
  private Image background;
  private Image playNow;
  private Image exitGame;
  private StateBasedGame game;

  @Override
  public void init(GameContainer container, StateBasedGame game) throws SlickException {
    this.game = game;
    this.background = new Image("main/resources/background/Hell.jpg");
    playNow = new Image("main/resources/playnow.png");
    exitGame = new Image("main/resources/exitGame.jpg");
    
  }

  @Override
  public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
    background.draw(0, 0, container.getWidth(), container.getHeight());
    g.drawString("Wellcome in Hell", 350, 150);
    /*g.drawString("Appuyez sur une Touche si vous vous en sentez Capable.", 200, 250);*/
    playNow.draw(250,250);
    
  }

  
  @Override
  public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	  int posX = Mouse.getX();
	  int posY = Mouse.getY();
	  
	  if((posX>250 && posX<597) &&(posY>250 && posY<358)){
		  if(Mouse.isButtonDown(0)){
			  game.enterState(MapGameState.ID);
		  }
	  }
	  
  }

  @Override
  public void keyReleased(int key, char c) {
    /*game.enterState(MapGameState.ID);*/
  }

  @Override
  public int getID() {
    return ID;
  }
}
