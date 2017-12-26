package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import shionn.slick.animation.AnimationListener;
import shionn.slick.animation.BezierPath;
import shionn.slick.animation.PathAnimation;

//methode pour creer un ennemi pour les combats
public class BattleEnnemy {
	private int pv = 12;
	private Image ennemy;
	private PathAnimation animation;

	//à l'initialisation on va chercher l'image et les animations
	public void init() throws SlickException {
		this.ennemy = new Image("battle/gobelin.png").getScaledCopy(2);
		this.animation = new PathAnimation(new BezierPath(0, 0, -400, 1, 50, 20, 0, 0), 2000);
	}

	//on ajoute un AnimationListener pour l'attaque, au debut et à la fin
	public void addAnimationListener(AnimationListener assignDamage, AnimationListener endAttack) {
		this.animation.addListener(1000, assignDamage);
		this.animation.addListener(2000, endAttack);
	}

	//methode pour reset les PV de l'ennemi
	public void reset() {
		this.pv = 12;
	}

	//methode pour afficher l'ennemi, on dessine le bon moment de l'animation au bon endroit
	public void render(GameContainer container, Graphics g) {
		
		Vector2f p = animation.currentLocation();
		this.ennemy.drawCentered(p.x + container.getWidth() * 3 / 4, p.y + container.getHeight()
				/ 2);
		Font font = g.getFont();
		String text = Integer.toString(pv);
		font.drawString(container.getWidth() * 3 / 4 - font.getWidth(text) / 2,
				container.getHeight() / 2 - ennemy.getHeight() / 2 - font.getLineHeight(), text,
				Color.white);
	}

	//on update l'animation
	public void update(int delta) {
		animation.update(delta);
	}

	//quand on attaque on demarre l'animaion
	public void startAttack() {
		animation.start();
	}

	//setters et getters
	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getPv() {
		return pv;
	}

}
