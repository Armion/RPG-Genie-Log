package Combat;
import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import character.Entitee;
import character.Joueur;
import competences.Competence;
import items.Item;
import map.MapGameState;
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
	private boolean debut=true;
	private int curseur=1;
	private int status=1;
	private Competence choix;
	ArrayList<Entitee> passage;
	int objet;
	
	
	private static final Color LIFE=new Color(255,0,0);
	private static final Color MANA=new Color(0,0,255);
	private static final Color WHITE=new Color(255,255,255);
	
	public CombatScreen()
	{
		
		
	}
	

	
	@Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
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
					
				 if(this.curseur<Team.getInstance().getInventory().getItem().size() &&this.curseur!=3)
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
						this.curseur=Team.getInstance().getInventory().getItem().size()-1;
							
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
					//TODO:Affichage des objets
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
				this.combat.actionJoueur(current, null, this.combat.ciblage(current, true).get(curseur));
				this.status=6;
				this.curseur=1;
			}
			else if(this.status==4 || this.status==5)
			{	
				if(this.status==4)
				this.combat.actionJoueur(current, this.choix, this.combat.ciblage(current, true).get(curseur));
				else
				this.combat.actionJoueur(current, this.choix, this.combat.ciblage(current, false).get(curseur));
				this.status=6;
				this.choix=null;
				this.curseur=1;
				
			}
			
			else if(this.status==11)
			{
				this.objet=this.curseur;
				if(((Consommable)Team.getInstance().getInventory().getItem().get(this.objet)).isTargatable()==true)	
				{
					this.status=12;
				
				}
				else this.status=13;
			}
			
			else if(this.status==12 || this.status==13)
			{
				String log="";
				this.combat.log.add(log);
				if(this.status==12)
				Team.getInstance().getInventory().getItem().get(this.objet).utiliser(this.combat.log.get(0),this.combat.ciblage(current, true).get(curseur) );
				else
				Team.getInstance().getInventory().getItem().get(this.objet).utiliser(this.combat.log.get(0),this.combat.ciblage(current, false).get(curseur) );
				
				
				this.status=6;
			}
			
			else if(this.status==6)
			{
				this.status=7;
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
			for( Entitee i : this.groupe)
			{
				i.setText("src/Combat/personnages/sprites/generique.png");
			}
			
		}
		
		this.background.draw(0,0,container.getWidth(),container.getHeight());
		affichagePerso(arg2,this.combat.getProta());
		
		this.menu.draw(0, 0, arg0.getWidth(),arg0.getHeight());
		if(this.actif==true)
		{afficherHUD(arg2,arg0);}
		if(tourJoueur==true)
		{
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
				for(int i=0;i<this.combat.log.size();i++)
				{
				
				arg2.drawString(this.combat.log.get(i),(arg0.getWidth()/12)*4, i*(arg0.getHeight()/19)+(arg0.getHeight()/5)*4);
				
				}
			}
			
			else if(this.status==11)
			{
				afficherObjets(arg2,arg0);
				afficherCurseur(arg2,arg0);
				
			}
				
			
			
		}
		else if(this.tourJoueur==false)
		{
			if(this.status==2)
			{
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
	
	public void afficherObjets(Graphics g,GameContainer con)
	{
		int j=0;
		for(Item i :Team.getInstance().getInventory().getItem())
		{
			if(i.fightUsable()==true)
			{
				if(j<4)
				g.drawString(i.getName(),(con.getWidth()/12)*4, j*(con.getHeight()/19)+(con.getHeight()/5)*4);
				else
				g.drawString(i.getName(),(con.getWidth()/12)*6, j*(con.getHeight()/19)+(con.getHeight()/5)*4);
				j++;
			}
			
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
		if(this.curseur>=4)
		{
			g.fillOval(((con.getWidth()/12)*6)-(con.getWidth()/55), (this.curseur-4)*(con.getHeight()/19)+(con.getHeight()/5)*4, 10, 10);
		}
		
		else
		{
			g.fillOval(((con.getWidth()/12)*4)-(con.getWidth()/55), this.curseur*(con.getHeight()/19)+(con.getHeight()/5)*4, 10, 10);
		}
	}



	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		
		if(this.actif==false && debut==false)
		{
			maitreCombat();
			
		}
		
		if(this.status==7)
		{
			maitreCombat();
		}
	
		if(this.tourJoueur==false && this.status==1 && debut==false)
		{
			this.combat.actionIA(current);
			this.status=2;
		}
		
		if(this.status==9)
		{
			maitreCombat();
		}
	
		
		if(this.status==10)
		{
			this.debut=true;
			this.actif=false;

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
			if(this.passage.size()>0)
			{this.passage.remove(0);}
			
			this.combat.retirerBlesse(passage);
			
			if(this.passage.size()==0)
			{
				this.actif=false;
			}
			else
			{
				this.current=this.passage.get(0);
				this.tourJoueur=this.current.isFriendly();
			}
			this.status=1;

			
			
			if(combat.conditionVictoire()==1)
			{
				this.status=8;
			}
			else if(combat.conditionVictoire()==2)
			{
				this.status=9;
			}
		}
		
		if(this.status==8)	
		{
			for(Entitee i : this.groupe)
			{
				this.combat.log.add('\n'+i.getXP(this.combat.getRecomp()));
			}
		}
		
		
		
		
		
	}
	
	


}
