package character;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import competences.*;
import effects.FightEffect;
import items.Item;


public abstract class Entitee {
	protected String nom;
	protected int pvMax;
	protected int pv;
	protected int atk;
	protected int def;
	protected int lvl;
	protected ArrayList<FightEffect> effets_subis=new ArrayList<FightEffect>();
	protected int manaMax;
	protected int mana;
	protected ArrayList<Competence> sorts=new ArrayList<Competence>();
	protected ArrayList<Item> equipements=new ArrayList<Item>();
	
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
		if(heal>this.pvMax-this.pv)
		{heal=this.pvMax-this.pv;}
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
	
	public abstract String getXP(int xp);
	
	
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
	
	public void setPV(int pv)
	{
		this.pv=pv;
	}
	
	
	public String subirComp(Competence sort)
	{ 
		String log="";
		
		if (sort.getDuree()>0)
		{
			this.effets_subis.add(new FightEffect(sort.getDuree(),sort.getBolus(),sort.getDegDurr(),sort.getNom()));
			if(sort.getBolus()!=null)
			{
			if(this.atk+sort.getBolus().get(0)>0)
				this.atk=this.atk+sort.getBolus().get(0);
			if(this.def+sort.getBolus().get(1)>0)
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
	
	public void retirerEffets()
	{

		boolean blesse=false;
		int compteur=0;
		while(blesse==false && compteur<this.effets_subis.size())
		{
			if(this.effets_subis.get(compteur).getDuree()==0)
			{
				if(this.effets_subis.get(compteur).getBolus()!=null)
				{
				this.atk=this.atk-this.effets_subis.get(compteur).getBolus().get(0);
				this.def=this.def-this.effets_subis.get(compteur).getBolus().get(1);
				}
				this.effets_subis.remove(this.effets_subis.get(compteur));
				
				
				retirerEffets();
				blesse=true;
			}
			
			compteur++;
		}
	}
	
	public String subirEffet()
	{
		retirerEffets();
		String log="";
		for (FightEffect i : this.effets_subis)
		{
			
			int deg=i.getDegheal();
			if(deg<0)
			{
				log=log+this.nom+" subis "+(-deg)+" points de degats de l'effet de "+i.getNom()+'\n';
				log=log+this.getDegats(-deg);
				
			}
			else if(deg>0)
			{
				log=log+this.getHeal(deg)+" venant de "+i.getNom()+'\n';
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
	
	public void recupMana(int mana)
	{
		this.mana=this.mana+mana;
		if(this.mana>this.manaMax)
		{
			this.mana=this.manaMax;
		}
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
	
	public ArrayList<FightEffect> getEffet()
	{
		return this.effets_subis;
	}
	

}
