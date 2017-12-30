package character;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import competences.*;
import effects.FightEffect;
import items.Item;
import items.equipements.Equipement;
import singleton.Team;
import singleton.log.LigneLog;
import singleton.log.Logs;


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
	protected ArrayList<Equipement> equipements=new ArrayList<Equipement>();
	protected Image portrait;
	
	
	protected int posX;
	protected int posY;
	public Animation[] anim=new Animation[1];
	
	
	public Entitee()
	{
		 try {
			portrait = new Image("resources/Icones/perso.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	
	public Image getPortrait()
	{
		return this.portrait;
	}
	
	public void setText(String path) throws SlickException
	{
		SpriteSheet sprite = new SpriteSheet(path,102,138);
		Animation anima= new Animation();
		anima.addFrame(sprite.getSprite(0, 0), 100);
		this.anim[0]=anima;
		
	}
	
	
	/*#####zone logique#####*/

		
	public void getDegats(int deg)
	{
		this.pv=this.pv-deg;
		
		if(this.pv>0)
		{
			
		}
		else 
		{
			Logs.getInstance().write(new LigneLog(this.getNom()+" est neutralisé !","Combat"));
			
		}
	}
	public void getHeal(int heal)
	{
		
		if(heal>this.pvMax-this.pv)
		{heal=this.pvMax-this.pv;}
		this.pv=this.pv+heal;
		Logs.getInstance().write(new LigneLog(this.nom+" regagne "+heal+" PV!"+'\n',"Combat"));
		
		if(this.pv>this.pvMax)
		{
			this.pv=this.pvMax;
		}
		
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
	
	public void setPV(int pv)
	{
		this.pv=pv;
	}
	
	
	public void subirComp(Competence sort)
	{ 
		
		
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
				Logs.getInstance().write(new LigneLog(this.nom+" subis "+(-degheal)+" points de degats de "+sort.getNom()+'\n',"Combat"));
				this.getDegats(-degheal);
				}
			else if(degheal>0)
				{
					this.getHeal(degheal);
				}
		
		
		
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
	
	public void subirEffet()
	{
		retirerEffets();
		
		for (FightEffect i : this.effets_subis)
		{
			
			int deg=i.getDegheal();
			if(deg<0)
			{
				Logs.getInstance().write(new LigneLog(this.nom+" subis "+(-deg)+" points de degats de l'effet de "+i.getNom()+'\n',"Combat"));
				this.getDegats(-deg);
				
			}
			else if(deg>0)
			{
				
				this.getHeal(deg);
			}
			
			i.reduireDurée();
			
		}
		
		
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
	
	
	private String addEquip(Equipement equip)
	{
		this.equipements.add(equip);
		this.atk=this.atk+equip.getBDeg();
		this.def=this.def+equip.getBDef();
		this.pvMax=this.pvMax+equip.getBPV();
		this.manaMax=this.manaMax+equip.getBMana();
		equip.setEquiped(true);
		Team.getInstance().getInventory().deleteItem(equip.getId());
		
		return this.nom+" equipe "+equip.getName();
	}
	
	public String EquiperItem(Equipement equip)
	{
		int type=equip.getType();
		int nb=0;
		for(Equipement i :this.equipements)
		{
			if(i.getType()==type)
			{
				nb++;
			}
		}
		
		if((nb==1 &&(type==1 || type==3))||nb==2 &&type==2)
		{
			return "Trop d'equipement de ce type déjà equipé !";
		}
		
		else
		{
			return addEquip(equip);
		}
		
	}
	
	public void DesequiperItem(Equipement equip)
	{
		boolean here=false;
		for(Equipement i : this.equipements)
		{
			if (i==equip)
			{
				here=true;
			}
		}
		
		if(here==true)
		{
			this.equipements.remove(equip);
			this.atk=this.atk-equip.getBDeg();
			this.def=this.def-equip.getBDef();
			this.pvMax=this.pvMax-equip.getBPV();
			this.manaMax=this.manaMax-equip.getBMana();
			equip.setEquiped(false);
			Team.getInstance().getInventory().getItemsList().add(equip);
		}
		
	}
	
	public ArrayList<Equipement> getEquip()
	{
		return this.equipements;
	}
	

}
