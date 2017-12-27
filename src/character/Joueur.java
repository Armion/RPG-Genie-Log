package character;

import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.SlickException;

public abstract class Joueur extends Entitee {
	
	protected int xp;
	

	protected abstract void UpgradeComp();
	
	
	protected String lvlUp()//TODO:Augmenter les carac(voir redéfinir dans la classe de perso)
	{
		String log="";
		log=log+this.getNom()+" monte d'un niveau !";
		this.xp=this.xp-(this.lvl*100);
		this.lvl=this.lvl+1;
		getXP(0);
		UpgradeComp();
		
		return log;
		
	}
	
	@Override
	public String getXP(int xp)
	{
		String log="";
		log=log+this.getNom()+" gagne "+xp+" points d'xp !";
		this.xp=this.xp+xp;
	
	
	if(this.xp>=this.lvl*100)
	{
		log=log+this.lvlUp();
	}
	
	return log;
		
	}
	
	@Override
	public boolean isFriendly()
	{
		return true;
	}
	
	


}
