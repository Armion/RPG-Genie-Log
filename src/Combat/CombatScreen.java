package Combat;
import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import character.Entitee;
import character.Joueur;
import competences.Competence;
import effects.FightEffect;
import items.Item;
import map.MapGameState;
import singleton.log.Logs;
import singleton.Team;
import items.consommables.*;;

public class CombatScreen extends BasicGameState {
	
	private GameContainer container;
	public static final int ID=3;
	private Combat combat;
	Image background;
	Image menu;
	Image hud;
	private ArrayList<Joueur> groupe;
	Music music;
	Music defeat;
	Music victory;
	private boolean tourJoueur=false;
	private boolean actif=false;
	private Entitee current;
	private Entitee cible;
	private boolean debut=true;
	private int curseur=1;
	private int status=0;
	private Competence choix;
	ArrayList<Entitee> passage;
	ArrayList<Item> objets;
	int objet;
	int compteur;
	boolean bloqueurLoot=false;
	Animation anim[]=new Animation[1];
	
	
	private static final Color LIFE=new Color(255,0,0);
	private static final Color MANA=new Color(0,0,255);
	private static final Color WHITE=new Color(255,255,255);
	private static final Color BLACK=new Color(0,0,0);
	
	public CombatScreen() throws SlickException
	{

		SpriteSheet sprite = new SpriteSheet("src/resources/sprites/Slash_2.png",102,138);
		Animation anima= new Animation();
		for(int i=0;i<5;i++)
		{
		anima.addFrame(sprite.getSprite(i, 0), 100);
		}
		this.anim[0]=anima;
	}
	

	
	@Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) 
        {
        	
            
        	 if(this.tourJoueur==true &&( this.status==3 || this.status==2 || this.status==4 || this.status==5 || this.status==11))
        		{this.status=1;}
        	else
        		this.status=10;
        }
        
	}
    
	
	@Override
	public void keyPressed(int key, char c)
	{
		if(this.tourJoueur==true)
		{
			if(key==Input.KEY_DOWN)
			{
				if(this.status==1)
				{
					if(this.curseur==3)
					{
						this.curseur=5;
					}
					else if(this.curseur<4 &&this.curseur!=3)
					{
						this.curseur++;
					}
					else this.curseur=1;
				}
				
				else if(this.status==2)
				{
					if(this.curseur<this.current.getComp().size())
					{
						this.curseur++;
					}
					
					else this.curseur=0;	
				}
				
				else if(this.status==3 || this.status==4 || this.status==12)
				{
					
					if(this.curseur<this.combat.ciblage(current, true).size()-1)
					{
						this.curseur++;
					}
					else
					{
						this.curseur=0;
					}
				}
				else if(this.status==5 || this.status==13)
				{
					if(this.curseur<this.combat.ciblage(current, false).size()-1)
					{
						this.curseur++;
					}
					else
					{
						this.curseur=0;
					}
					
				}
				else if(this.status==11)
				{
					
				 if(this.curseur<this.objets.size()-1)
					{
						this.curseur++;
					}
					else this.curseur=0;
				}
				
				
			}
			
		
			if(key==Input.KEY_UP)
			{

				if(this.status==1)
				{
					if(this.curseur==5)
					{
						this.curseur=4;
					}
					if(this.curseur>1 && this.curseur!=5)
					{
						this.curseur--;
					}
					else this.curseur=5;
				}
				
				else if(this.status==2)
				{
					if(this.curseur>0)
					{
						this.curseur--;
					}
					
					else this.curseur=this.current.getComp().size();	
				}
				
				else if(this.status==3 || this.status==5 || this.status==4 || this.status==12 || this.status==13)
				{
					if(this.curseur>0)
					{
						this.curseur--;
					}
					else
					{
						if(this.status==3 || this.status==4)
						this.curseur=this.combat.ciblage(current, true).size()-1;
						else
						this.curseur=this.combat.ciblage(current, false).size()-1;
					}
				
			}
				else if(this.status==11)
				{
					
				 if(this.curseur>0 )
					{
						this.curseur--;
					}
					else 
						this.curseur=this.objets.size()-1;
							
				}
			
			
		}
		
		
		if(key==Input.KEY_ENTER)
		{
			if(this.status==1)
			{
				if(this.curseur==1)
				{
					this.curseur=0;
					this.status=3;
				}
				
				
				
				else if(this.curseur==2)
				{
					this.curseur=0;
					this.status=2;
				}
				else if(this.curseur==3)
				{
					this.curseur=0;
					this.status=11;
					this.objets=new ArrayList<Item>();
					for(Item i : Team.getInstance().getInventory().getItemsList())
					{
						if(i.fightUsable()==true)
						{
							this.objets.add(i);
						}
					}
				}
				
				else if(this.curseur==5)
				{
					
				this.status=9;
				}
			}
			
			else if(this.status==2)
			{
				if(this.curseur==this.current.getComp().size())
				{
					this.status=1;
					this.curseur=1;
				}
				else
				{
					
					this.choix=this.current.getComp().get(curseur);
					if(this.choix.getCible()==true)
					{
						this.status=4;
					}
					else if(this.choix.getCible()==false)
					{
						this.status=5;
					}
					this.curseur=0;
				}
			}
			
			
			
			else if(this.status==3)
			{
				this.cible= this.combat.ciblage(current, true).get(curseur);
				this.combat.actionJoueur(current, null, this.combat.ciblage(current, true).get(curseur));
				
				this.combat.getLog();
				this.status=6;
				this.curseur=1;
			}
			else if(this.status==4 || this.status==5)
			{
				
				if(this.status==4)
				{
				this.cible=this.combat.ciblage(current, true).get(curseur);
				this.combat.actionJoueur(current, this.choix, this.combat.ciblage(current, true).get(curseur));
				}
				else
				{
				this.cible=this.combat.ciblage(current, false).get(curseur);
				this.combat.actionJoueur(current, this.choix, this.combat.ciblage(current, false).get(curseur));
				}
				
				if(this.choix.path!=null)
				{
					System.out.println(this.choix.getNom());
					try {
						this.choix.genererAnim();
					} catch (SlickException e) {
						e.printStackTrace();
					}
				}
				
				
				this.combat.getLog();
				this.status=6;
				this.curseur=1;
				
			}
			
			else if(this.status==11)
			{
				this.objet=this.curseur;
				if(((Consommable)this.objets.get(objet)).isTargatable()==true)	
				{
					this.status=12;
					this.curseur=0;
				}
				else this.status=13;
				this.curseur=0;
				
				
			}
			
			else if(this.status==12 || this.status==13)
			{
				String log="";
				this.combat.log.add(log);
				if(this.status==12)
				{
					
				
				Team.getInstance().getInventory().useItem(this.objet,this.combat.ciblage(current, true).get(curseur), this.combat.log.get(0));
				}
				else {
				
				Team.getInstance().getInventory().useItem(this.objet,this.combat.ciblage(current, false).get(curseur), this.combat.log.get(0));
				}
				
				
				this.combat.getLog();
				
				this.status=6;
				this.curseur=0;
			}
			
			else if(this.status==6)
			{
				
				
				this.status=7;
				this.curseur=1;
				
			}
			
		}
		
		}
		else if(this.tourJoueur==false)
		{
			if(key==Input.KEY_ENTER)
			{
				
				if(this.status==2)
				{
					this.status=7;
				}
			}
			
		}
		
		if(key==Input.KEY_ENTER && (this.status==8 || this.status==9) )
		{
			if(this.combat.log.size()>=3)
			{
				for(int i=0;i<3;i++)
				this.combat.log.remove(0);
			}
			else	
			this.status=10;
		}
		
	}



	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		this.groupe=(ArrayList<Joueur>) Team.getInstance().getTeam();
		this.container=arg0;
		this.background=new Image("src/Combat/background/battle.jpg");
		this.music=new Music("src/Combat/battle-song.ogg");
		this.menu=new Image("src/Combat/background/interface.png");
		this.hud=new Image("src/Combat/personnages/sprites/hud.png");
		
		
		
	}
	
	public void affichagePerso(Graphics g, ArrayList<Entitee> prota)
	{
		for(Entitee i : prota)
		{
			g.drawAnimation(i.anim[0],i.getX(),i.getY());
			
		}
	}



	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		
	
		
		this.background.draw(0,0,container.getWidth(),container.getHeight());
		arg2.drawString("Round "+this.combat.round, arg0.getWidth()/2, 0);
		affichagePerso(arg2,this.combat.getProta());
		
		this.menu.draw(0, 0, arg0.getWidth(),arg0.getHeight());
		if(this.actif==true)
		{afficherHUD(arg2,arg0);}
		if(tourJoueur==true)
		{
			if(this.status>=1 && this.status<=6)
			{
				arg2.setColor(BLACK);
				arg2.fillOval(this.current.getX()-(arg0.getWidth()/8), this.current.getY()+50, 10, 10);
				arg2.setColor(WHITE);
			}
			if(this.status==1)
			{
			afficherAction(arg2,arg0);
			afficherCurseur(arg2,arg0);
			}
			else if(this.status==2)
			{
				
				
				afficherComp(arg2,arg0);
				
				afficherCurseur(arg2,arg0);
			}
			else if(this.status==3 || this.status==4 || this.status==12)
			{
				
				afficherCibles(arg2,arg0,true);
				afficherCurseur(arg2,arg0);
			}
			
			else if(this.status==5 || this.status==13)
			{
				afficherCibles(arg2,arg0,false);
				afficherCurseur(arg2,arg0);
			}
			
			else if(this.status==6)
			{
				if(this.objets==null && (this.choix==null || this.choix.path!=null))
					{afficherCompetence(arg2,arg0);}
				for(int i=0;i<this.combat.log.size();i++)
				{
				
				arg2.drawString(this.combat.log.get(i),(arg0.getWidth()/12)*4, i*(arg0.getHeight()/19)+(arg0.getHeight()/5)*4);
					
				
				}
			}
			
			else if(this.status==11)
			{
				afficherObjets(arg2,arg0,0);
				afficherCurseur(arg2,arg0);
				
			}
				
			
			
		}
		else if(this.tourJoueur==false)
		{
			if(this.status==2)
			{
				
				
				if(this.choix==null || this.choix.path!=null)
				afficherCompetence(arg2,arg0);
				
				for(int i=0;i<this.combat.log.size();i++)
				{
				
				arg2.drawString(this.combat.log.get(i),(arg0.getWidth()/12)*4, i*(arg0.getHeight()/19)+(arg0.getHeight()/5)*4);
				
				}

			}
			
			
		}
		
		if(this.status==8)
		{
			arg2.drawString("Victoire !",(arg0.getWidth()/12)*4, 0*(arg0.getHeight()/19)+(arg0.getHeight()/5)*4);
			for(int i=0;i<this.combat.log.size();i++)
			{
			
			arg2.drawString(this.combat.log.get(i),(arg0.getWidth()/12)*4, (i+1)*(arg0.getHeight()/19)+(arg0.getHeight()/5)*4);
			
			}
			
			
		
		}
		
		if(this.status==9)
		{
			arg2.drawString("Défaite...",(arg0.getWidth()/12)*4, 0*(arg0.getHeight()/19)+(arg0.getHeight()/5)*4);
			
				
		
		
		}
		
		
			
	
	}
	public void afficherCompetence(Graphics g,GameContainer con) throws SlickException
	{
		if(this.choix!=null)
		g.drawAnimation(this.choix.anim[0], this.cible.getX(), this.cible.getY());
		else
		g.drawAnimation(this.anim[0], this.cible.getX(), this.cible.getY());
			
	}
	
	public void afficherObjets(Graphics g,GameContainer con,int page)
	{
		int nb;
		Item j;
		if(this.curseur<(8*(page+1)) && this.curseur>=8*(page))
		{
		if(this.objets.size()-(8*page)>8)
		{nb=8*(page+1);}
		else
		{
			nb=this.objets.size();
		}
		
		
			for(int i=8*page;i<nb;i++)
			{
				j=this.objets.get(i);
			
				if(8*page==0)
				{
				
				if(i%8<4)
				g.drawString(j.getName(),(con.getWidth()/12)*4, (i/((8*page)+1))*(con.getHeight()/19)+(con.getHeight()/5)*4);
				else
				g.drawString(j.getName(),(con.getWidth()/12)*6, (i-4)/((8*page)+1)*(con.getHeight()/19)+(con.getHeight()/5)*4);
				}
				else
				{
					if(i%8<4)
						g.drawString(j.getName(),(con.getWidth()/12)*4, (i-((8*page)))*(con.getHeight()/19)+(con.getHeight()/5)*4);
						else
						g.drawString(j.getName(),(con.getWidth()/12)*6, (i-((8*page))-4)*(con.getHeight()/19)+(con.getHeight()/5)*4);
					
				}
			
			}
			afficherCurseur(g,con);
		}
		
	
		
			else if(this.curseur>=(8*(page+1))-1)
			{
				afficherObjets(g,con,page+1);
				afficherCurseur(g,con);
				
			}
		
		
		
			else if(this.curseur<8*(page))
			{
				afficherObjets(g,con,page-1);
				afficherCurseur(g,con);
			}
			
			
	}
		
		
		 
	
	
	public void afficherHUD(Graphics g,GameContainer con)
	{
		for(Entitee i : this.combat.getProta())
		{
			float pv=(float)(((float)i.getPV()/i.getPVMax())*(float)96);
			float mana=(float)(((float)i.getMana()/i.getManaMax())*(float)96);
			if(pv<0)
			{
				pv=0;
			}
			if(mana<0)
			{
				mana=0;
			}
			if(i.isFriendly()==true)
			{
			g.setColor(LIFE);
			g.fillRect(i.getX()-(con.getWidth()/10), 50+i.getY(), pv, 9);
			g.setColor(MANA);
			g.fillRect(i.getX()-(con.getWidth()/10), 50+i.getY()+9, mana, 9);
			g.drawImage(this.hud,(float)i.getX()-(con.getWidth()/10)-2 ,(float)50+i.getY()-2);
			g.setColor(WHITE);
			}
			else if(i.isFriendly()==false)
			{
				g.setColor(LIFE);
				g.fillRect(i.getX()+(con.getWidth()/10), 50+i.getY(), pv, 9);
				g.setColor(MANA);
				g.fillRect(i.getX()+(con.getWidth()/10), 50+i.getY()+9, mana, 9);
				g.drawImage(this.hud,(float)i.getX()+(con.getWidth()/10)-2 ,(float)50+i.getY()-2);
				g.setColor(WHITE);
				
			}
			
		}
		
	}
	
	public void afficherCibles(Graphics g,GameContainer con,boolean allies)
	{
		ArrayList<Entitee> cibles=this.combat.ciblage(this.current, allies);
		for(int i=0;i<cibles.size();i++)
		{
			g.drawString(cibles.get(i).getNom()+"(PV:"+cibles.get(i).getPV()+")",(con.getWidth()/12)*4, i*(con.getHeight()/19)+(con.getHeight()/5)*4);
			
		}
		
		
	}
	
	public void afficherComp(Graphics g,GameContainer con)
			{
			int i=0;
				for( i=0;i<this.current.getComp().size();i++)
				{
					g.drawString(this.current.getComp().get(i).getNom()+"("+this.current.getComp().get(i).getCout()+")",(con.getWidth()/12)*4, i*(con.getHeight()/19)+(con.getHeight()/5)*4);
				}
				
				g.drawString("Retour",(con.getWidth()/12)*4, i*(con.getHeight()/19)+(con.getHeight()/5)*4);
		
			}
	
	public void afficherAction(Graphics g,GameContainer con)
	{	g.drawString("Action de "+this.current.getNom()+"(PV:"+this.current.getPV()+"/Mana:"+this.current.getMana()+")",(con.getWidth()/12)*4, 0*(con.getHeight()/19)+(con.getHeight()/5)*4);
		g.drawString("Attaque",(con.getWidth()/12)*4, 1*(con.getHeight()/19)+(con.getHeight()/5)*4);
		g.drawString("Competences",(con.getWidth()/12)*4, 2*(con.getHeight()/19)+(con.getHeight()/5)*4);
		g.drawString("Objet",(con.getWidth()/12)*4, 3*(con.getHeight()/19)+(con.getHeight()/5)*4);
		g.drawString("Fuir",(con.getWidth()/12)*6, 1*(con.getHeight()/19)+(con.getHeight()/5)*4);
	}
	
	public void afficherCurseur(Graphics g,GameContainer con)
	{
		if(this.curseur%8>=4)
		{
			g.fillOval(((con.getWidth()/12)*6)-(con.getWidth()/55), ((this.curseur-4)%8)*(con.getHeight()/19)+(con.getHeight()/5)*4, 10, 10);
		}
		
		else
		{
			g.fillOval(((con.getWidth()/12)*4)-(con.getWidth()/55), (this.curseur%8)*(con.getHeight()/19)+(con.getHeight()/5)*4, 10, 10);
		}
	}



	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
		
		
		if(!music.playing())
		{
			  music.loop();
		}
		
		if(debut==true)
		{
			this.status=1;
			this.combat=new Combat(groupe,arg0);
			this.combat.debutCombat(0);
			debut=false;
			this.bloqueurLoot=false;
			Logs.getInstance().deleteType("Combat");
			Logs.getInstance().deleteType("Effect");
			this.objet=-1;
			this.cible=null;
			this.choix=null;
			this.current=null;
			
		}
		
		if(this.actif==false && debut==false)
		{
			maitreCombat();
			
		}
		
	
	
		if(this.tourJoueur==false && this.status==1 && debut==false && arg2>10)
		{	
			if(this.compteur==0)
			{	
		
			this.combat.actionIA(this.current);
			this.choix=combat.choixIA;
			this.cible=combat.cibleIA;
			this.combat.getLog();
			if(this.choix!=null && this.choix.path!=null)
			{
			this.choix.genererAnim();
			}
			this.status=2;
			this.compteur=1;
			}

	
		}
		
		if(this.status==7)
		{
			maitreCombat();
		}
		
		
		if(this.status==9)
		{
			maitreCombat();
		}
	
		
		if(this.status==10)
		{
			this.debut=true;
			this.actif=false;
			Logs.getInstance().deleteType("Combat");
			Logs.getInstance().deleteType("Effect");
			this.status=0;
			for(Entitee i :this.groupe)
			{
				if(i.getPV()<=0)
				{
					i.setPV(1);
				}
				i.recupMana(i.getManaMax());
			}
			arg1.enterState(MapGameState.ID);
		}
		
	}



	@Override
	public int getID() {
		
		return this.ID;
	}
	
	
	public void maitreCombat()
	{
		
		if(this.actif==false)
		{
			this.combat.mainCombat();
		passage = this.combat.getPassage();
		this.current=passage.get(0);
		this.tourJoueur=this.current.isFriendly();
		this.actif=true;
		}
		
		
		if(this.status==7)
		{
			
			this.combat.log=new ArrayList<String>();
			Logs.getInstance().deleteType("Combat");
			Logs.getInstance().deleteType("Effect");
			this.choix=null;
			if(this.passage.size()>0)
			{this.passage.remove(0);}
			
			this.combat.retirerBlesse(passage);
			
			if(this.passage.size()==0)
			{
				this.combat.round++;
				this.actif=false;
			}
			else if(this.passage.size()>0)
			{
				this.current=this.passage.get(0);
				this.tourJoueur=this.current.isFriendly();
			}
			

			
			this.status=1;
			if(combat.conditionVictoire()==1)
			{
				this.combat.log=new ArrayList<String>();
				Logs.getInstance().deleteType("Combat");
				this.combat.getLog();
				this.status=8;
			}
			else if(combat.conditionVictoire()==2)
			{
				this.status=9;
			}
			this.compteur=0;
			
		}
		
		if(this.status==8)	
		{
			if(this.bloqueurLoot==false)
			{
			
			for(Entitee i : this.groupe)
			{
			
				i.getXP(this.combat.getRecomp());
			}
			this.combat.loot();
			this.combat.getLog();
			this.bloqueurLoot=true;
			}
		}
		
		
		
		
		
	}
	
	


}
