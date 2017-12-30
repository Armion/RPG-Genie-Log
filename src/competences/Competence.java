package competences;

import java.util.ArrayList;


import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
public abstract class Competence {
	public String path;
	protected String nom;
	protected boolean cible; //true=ennemis false =alli�es
	protected int zone;//1=1cibles, 0=self,2:groupe
	protected int duree;//0=instant, 1=1 round
	protected ArrayList<Integer> bolus;//0=bolus sur l'atk, 1=bolus sur la def
	protected int degheal;
	protected int deghealDurr;
	protected int cout; // cout en mana
	protected int tag;//1=offensif,2=support,3=soins;
	protected boolean outOfFight;
	public int tailleAnim;
	public Animation[] anim;
	int x;
	int y;
	protected Image icone = null;
	
	
	public int getDegDurr()
	{
		return this.deghealDurr;
	}
	public int getDuree()
	{
		return this.duree;
	}
	
	public boolean getCible()
	{
		return this.cible;
	}
	
	public int getZone()
	{
		return this.zone;
	}
	
	public boolean usableOutOfFight()
	{
		if(outOfFight)
			return true;
		return false;
	}
	
	
	public ArrayList<Integer> getBolus()
	{
		return this.bolus;
	}
	
	public int getDeg()
	{
		return this.degheal;
	}
	
	public int getCout()
	{
		return this.cout;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public int getTag()
	{
		return this.tag;
	}
	public void genererAnim() throws SlickException
	{

		SpriteSheet sprite = new SpriteSheet(this.path,x,y);
		Animation anima= new Animation();
		for(int i=0;i<this.tailleAnim;i++)
		{
		anima.addFrame(sprite.getSprite(i, 0), 150);
		
		}
		
	
		this.anim[0]=anima;
	}
	
	
	public Image getIcone()
	{
		return this.icone;
	}
}
