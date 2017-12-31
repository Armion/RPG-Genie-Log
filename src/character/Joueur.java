package character;

import singleton.log.LigneLog;
import singleton.log.Logs;
//Sous classe d'entité qui permet de définir des classes de joueurs
public abstract class Joueur extends Entitee {
	
	protected int xp;
	protected String path;
	

	protected abstract void UpgradeComp();
	
	public Joueur()
	{
		super();
	}
	
	
	protected String lvlUp()
	{
		String log="";
		log=log+this.getNom()+" monte d'un niveau ! ";
		Logs.getInstance().write(new LigneLog(this.getNom()+" monte d'un niveau ! ","Combat"));
		this.xp=this.xp-(this.lvl*100);
		this.lvl=this.lvl+1;
		getXP(0);
		UpgradeComp();
		
		return log;
		
	}
	
	@Override
	public void getXP(int xp)
	{
		String log="";
		log=log+this.getNom()+" gagne "+xp+" points d'xp !";
		Logs.getInstance().write(new LigneLog(this.getNom()+" gagne "+xp+" points d'xp ! ","Combat"));
		this.xp=this.xp+xp;
	
	
	if(this.xp>=this.lvl*100)
	{
		log=log+this.lvlUp();
	}
	
	
		
	}
	
	@Override
	public boolean isFriendly()
	{
		return true;
	}
	
	public String getPath()
	{
		return this.path;
	}
	
	


}
