package map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import character.Character;

//classe qui repr�sente le joueur sur la map
public class MapPlayer {

	private float x = 300, y = 300;
	private boolean onStair = false;
	private Animation[] animations = new Animation[8];
	private float dx = 0, dy = 0;
	private int direction;

	private Map map;

	//constructeur qui va charger la map du joueur
	public MapPlayer(Map map) {
		this.map = map;
	}

	//init qui va charger les bons sprites pour faire l'animation
	public void init() throws SlickException {
		
		//on charge l'animation
		this.animations = Character.createAnime("resources/sprites/character.png", 64, 64);
	}
	
	
	


	//methode pour afficher le rendu du perso avec une petite ombre sympas
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval((int) x - 16, (int) y - 8, 32, 16);
		g.drawAnimation(animations[direction + (isMoving() ? 4 : 0)], (int) x - 32, (int) y - 60);
	}

	//methode d'update du joueur
	public void update(int delta) {
		//on a besoin d'update si le joueur bouge en v�rifiant la collision
		if (this.isMoving()) 
		{
			updateDirection();
			float futurX = getFuturX(delta);
			float futurY = getFuturY(delta);
			boolean collision = this.map.isCollision(futurX, futurY);
			if (collision) {
				stopMoving();
			} else {
				this.x = futurX;
				this.y = futurY;
			}
		}
	}

	//getters et setters
	private float getFuturX(int delta) {
		return this.x + .15f * delta * dx;
	}

	private float getFuturY(int delta) {
		float futurY = this.y + .15f * delta * dy;
		if (this.onStair) {
			futurY = futurY - .15f * dx * delta;
		}
		return futurY;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	//on peut aussi etre oblig� d'update la direction du joueur
	private void updateDirection() {
		if (dx > 0 && dx >= Math.abs(dy)) {
			direction = 3;
		} else if (dx < 0 && -dx >= Math.abs(dy)) {
			direction = 1;
		} else if (dy < 0) {
			direction = 0;
		} else if (dy > 0) {
			direction = 2;
		}
	}

	//on oubli pas qu'on travail avec des vecteurs
	public void setDirection(int direction) {
		switch (direction) {
		case 0:
			dx = 0;
			dy = -1;
			break;
		case 1:
			dx = -1;
			dy = 0;
			break;
		case 2:
			dx = 0;
			dy = 1;
			break;
		case 3:
			dx = 1;
			dy = 0;
			break;
		default:
			dx = 0;
			dy = 0;
			break;
		}
	}

	//pour savoir si le joueur se deplace
	public boolean isMoving() {
		return dx != 0 || dy != 0;
	}

	//pour que le joueur ne bouge plus
	public void stopMoving() {
		dx = 0;
		dy = 0;
	}

	//pour savoir si le joueur est sur un escalier
	public boolean isOnStair() {
		return onStair;
	}

	public void setOnStair(boolean onStair) {
		this.onStair = onStair;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

}