package character;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import Combat.competences.*;


public abstract class Entitee {
	protected String nom;
	protected int pvMax;
	protected int pv;
	protected int atk;
	protected int def;
	protected int lvl;
	protected ArrayList<Effet> effets_subis=new ArrayList<Effet>();
	protected int manaMax;
	protected int mana;
	protected ArrayList<Competence> sorts=new ArrayList<Competence>();
	
	protected int posX;
	protected int posY;
	public Animation[] anim=new Animation[1];
	
	
	
	/*#####Zone graphique####*/
	
	public int getX()
	{
		return this.posX;
	}
	
	public int getY()
	{
		return this.posY;
	}
	
	public void setX(int x)
	{
		this.posX=x;
	}
	
	public void setY(int y)
	{
		this.posY=y;
	}
	
	public void setText(String path) throws SlickException
	{
		SpriteSheet sprite = new SpriteSheet(path,102,138);
		Animation anima= new Animation();
		anima.addFrame(sprite.getSprite(0, 0), 100);
		this.anim[0]=anima;
		
	}
	
	
	/*#####zone logique#####*/

		
	public String getDegats(int deg)
	{String log="";
		this.pv=this.pv-deg;
		
		if(this.pv>0)
		{
			return log;
		}
		else 
		{
			log=log+this.getNom()+" est neutralisé !";
			return log;
		}
	}
	public String getHeal(int heal)
	{
		String log="";
		this.pv=this.pv+heal;
		log=log+this.nom+" regagne "+heal+" PV!"+'\n';
		
		if(this.pv>this.pvMax)
		{
			this.pv=this.pvMax;
		}
		return log;
	}
	
	public int getAtk()
	{
		return this.atk;
	}
	
	
	public int getDef()
	{
		return this.def;
	}
	
	

	public int getLVL()
	{
		return this.lvl;
	}
	


	
	public abstract boolean isFriendly();
	
	public abstract void getXP(int xp);
	
	
	public void reduireMana(int cout)
	{
		this.mana=this.mana-cout;
	}
	

	public ArrayList<Competence> getComp()
	{
		return this.sorts;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public int getPV()
	{
		return this.pv;
	}
	
	
	
	public String subirComp(Competence sort)
	{ 
		String log="";
		
		if (sort.getDuree()>0)
		{
			this.effets_subis.add(new Effet(sort.getDuree(),sort.getBolus(),sort.getDegDurr(),sort.getNom()));
			if(sort.getBolus()!=null)
			{
			this.atk=this.atk+sort.getBolus().get(0);
			this.def=this.def+sort.getBolus().get(1);
			}
			
		}
		
		
		
			int degheal=sort.getDeg();
			if(degheal<0)
				{
				log=log+this.nom+" subis "+(-degheal)+" points de degats de "+sort.getNom()+'\n';
					log=log+this.getDegats(-degheal)+'\n';
				}
			else if(degheal>0)
				{
					log=log+this.getHeal(degheal)+'\n';
				}
		
		return log;
		
	}
	
	public String subirEffet()
	{
		String log="";
		for (Effet i : this.effets_subis)
		{
			if(i.getDuree()==0)
			{
				this.atk=this.atk-i.getBolus().get(0);
				this.def=this.def-i.getBolus().get(1);
				this.effets_subis.remove(i);
			}
			
			int deg=i.getDegheal();
			if(deg<0)
			{
				log=log+this.nom+" subis "+(-deg)+" points de degats de l'effet de "+i.getNom()+'\n';
				log=log+this.getDegats(-deg);
				
			}
			else if(deg>0)
			{
				log=log+this.getHeal(deg);
			}
			
			i.reduireDurée();
			
		}
		
		return log;
	}
	
	public ArrayList<Competence> getSorts()
	{
		return this.sorts;
	}
	
	public int getAttack()
	{
		return this.atk;
	}
	
	public int getManaMax()
	{
		return this.manaMax;
	}
	
	public int getMana()
	{
		return this.mana;
	}
	
	public boolean fullLife()
	{
		if(this.pv==this.pvMax)
		{
			return true;
		}
		
		else return false;
	}
	
	public int getPVMax()
	{
		return this.pvMax;
	}
	
	public ArrayList<Effet> getEffet()
	{
		return this.effets_subis;
	}
	

}
