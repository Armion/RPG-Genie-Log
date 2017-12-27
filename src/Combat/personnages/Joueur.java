package Combat.personnages;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.SlickException;

public abstract class Joueur extends Entitee {
	
	protected int xp;
	
	/*public Joueur() throws SlickException
	{
		this.setText("src/personnages/sprites/generique.png");
	}
	*/
	
	private void lvlUp()//TODO:Augmenter les carac(voir redéfinir dans la classe de perso)
	{
		System.out.println(this.getNom()+" monte d'un niveau !");
		this.xp=this.xp-(this.lvl*100);
		this.lvl=this.lvl+1;
		getXP(0);
	}
	
	@Override
	public void getXP(int xp)
	{
		System.out.println(this.getNom()+" gagne "+xp+" points d'xp !");
	this.xp=this.xp+xp;
	
	
	if(this.xp>=this.lvl*100)
	{
		this.lvlUp();
	}
		
	}
	
	@Override
	public boolean isFriendly()
	{
		return true;
	}
	
	


}
