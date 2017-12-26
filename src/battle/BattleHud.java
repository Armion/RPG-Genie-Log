package battle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;

import shionn.slick.ui.TextArea;
import shionn.slick.ui.align.VerticalAlignement;

//HUD de combat
public class BattleHud implements ComponentListener {

	private static final int Y_PADDING = 3;
	private static final int X_PADDING = 13;
	private static final int SPACE = 5;
	private BattleController controller;
	private MouseOverArea attackButton;
	private MouseOverArea fleeButton;
	private MouseOverArea defendButton;
	private TextArea log;

	
	public BattleHud(BattleController controller) {
		this.controller = controller;
		this.controller.setHud(this);
	}

	//à l'initialisation on va charger les images
	public void init(GameContainer container) throws SlickException {
		Image buttonImage = new Image("resources/hud/button.png");
		attackButton = new MouseOverArea(container, buttonImage, SPACE, container.getHeight()
				- (buttonImage.getHeight() + SPACE) * 3, this);
		defendButton = new MouseOverArea(container, buttonImage, SPACE, container.getHeight()
				- (buttonImage.getHeight() + SPACE) * 2, this);
		fleeButton = new MouseOverArea(container, buttonImage, SPACE, container.getHeight()
				- (buttonImage.getHeight() + SPACE) * 1, this);
		log = new TextArea(SPACE + attackButton.getWidth() + SPACE, attackButton.getY(),
				container.getWidth() - attackButton.getWidth() - SPACE * 3, buttonImage.getHeight()
						* 3 + SPACE * 2);
		log.setBottomUp(true);
		log.setDefaultFont(container.getDefaultFont());
	}

	//pour afficher l'HUD
	public void render(GameContainer container, Graphics g) {
		g.setColor(Color.black);
		attackButton.render(container, g);
		g.drawString("Attaquer", attackButton.getX() + X_PADDING, attackButton.getY() + Y_PADDING);
		defendButton.render(container, g);
		g.drawString("Defendre", defendButton.getX() + X_PADDING, defendButton.getY() + Y_PADDING);
		fleeButton.render(container, g);
		g.drawString("Fuire", fleeButton.getX() + X_PADDING, fleeButton.getY() + Y_PADDING);
		log.render();
	}

	//on regarde quand on objet de l'HUD est utilisé pour appeller la bonne commande
	@Override
	public void componentActivated(AbstractComponent source) {
		if (source == attackButton) {
			controller.controlPressed(BattleCommand.ATTACK);
		} else if (source == defendButton) {
			controller.controlPressed(BattleCommand.DEFEND);
		} else if (source == fleeButton) {
			controller.controlPressed(BattleCommand.FLEE);
		}
	}

	//methode pour afficher du text au centre en bas
	public void addLog(String text) {
		log.addFirstText(text, VerticalAlignement.LEFT);
	}
}
