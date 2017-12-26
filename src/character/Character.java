package character;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public abstract class Character {
	
	protected int attackMax;
	protected int attack;
	protected int deffMax;
	protected int deff;
	protected int pvMax;
	protected int pv;
	protected int mana;
	protected int manaMax;
	protected int niveau;
	protected String nom;
	
	
	public static Animation[] createAnime(String path, int x, int y) throws SlickException
	{
	
		//on creer un nouveau sprite, on lui donne les dimensions des images pour l'animation
		SpriteSheet sprite = new SpriteSheet(path, x, y);
		
		//dans notre cas il y 4 directions + 4 cas de repos
		Animation[] anime = new Animation[8];
		
		//on remplit le tableau d'animations 
		
		//d'abord les etats de repos, ils ne comprennent que la premiere image de 0 à 1, à la ligne i correspondant
		for(int i = 0; i < 4; i++)
		{
			anime[i] = loadAnimation(sprite, 0, 1, i);
		}
		
		//ensuite les etats pour bouger qui comprennent les 7 images de mouvement, on a i qui vas aller de 4 à 7, donc on fait i-4 pour ne pas utiliser une autre variable
		for(int i = 4; i < 8; i++)
		{
			anime[i] = loadAnimation(sprite, 1, 9, i-4);
		}
		
		return anime;
	}

	//methode qui va creer une animation en chargeant les images du sprite
	public static Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
	    Animation animation = new Animation();
	    
	    //on charge les x images de la ligne y, en mettant 100ms entre les animations
	    for (int x = startX; x < endX; x++) {
	        animation.addFrame(spriteSheet.getSprite(x, y), 100);
	    }
	    return animation;
	}
	
	public void recevoirDegats(int degats)
	{
		this.pv -= degats*1/(0.01*this.deff);
		
		if(this.pv < 0)
			this.pv = 0;
		
	}
	
	public void attaquer(Character cible)
	{
		cible.recevoirDegats(this.attack);

	}
	
	public boolean isAlive()
	{
		return this.pv > 0;
	}

}
