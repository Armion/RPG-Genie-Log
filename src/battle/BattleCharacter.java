package battle;

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

import character.*;
import character.Character;


//classe pour afficher le guerrier
public class BattleCharacter {

	private Image hero;
	private PathAnimation animation;
	private Guerrier perso;
	
	public BattleCharacter(Guerrier perso)
	{
		this.perso = perso;
	}

	public void init() throws SlickException {
		this.hero = new Image("resources/battle/hero.png").getScaledCopy(2);
		this.animation = new PathAnimation(new BezierPath(0, 0, 400, 1, -50, 20, 0, 0), 2000);
		this.perso = new Guerrier();
	}

	public void addAnimationListener(AnimationListener assignDamage, AnimationListener endAttack) {
		this.animation.addListener(1000, assignDamage);
		this.animation.addListener(2000, endAttack);
	}

	public void render(GameContainer container, Graphics g) {
		Vector2f p = animation.currentLocation();
		hero.drawCentered(p.x + container.getWidth() * 1 / 4, p.y + container.getHeight() / 2);
		Font font = g.getFont();
		String text = Integer.toString(this.perso.getPv());
		font.drawString(container.getWidth() * 1 / 4 - font.getWidth(text) / 2,
				container.getHeight() / 2 - hero.getHeight() / 2 - font.getLineHeight(), text,
				Color.white);
	}

	public void update(int delta) {
		animation.update(delta);
	}

	public void startAttack() {
		animation.start();
	}
	
	public int getPv() {
		return this.perso.getPv();
	}
	
	public void attaquer(Character cible)
	{
		this.perso.attaquer(cible);
	}
	
	public int getAttack()
	{
		return perso.getAttack();
	}
	
	public void recevoirDegats(int degats)
	{
		perso.recevoirDegats(degats);
	}
	
	public boolean isAlive()
	{
		return perso.isAlive();
	}
	
	public Character getCible()
	{
		return this.perso;
	}

	public void reset()
	{
		this.perso.reset();
	}

}
