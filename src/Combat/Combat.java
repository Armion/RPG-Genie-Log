package Combat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;

import Combat.personnages.*;
import character.DarkKnight;
import character.Entitee;
import character.Joueur;
import character.Liche;
import character.Squelette;
import competences.*;

public class Combat {
	
	private int round;
	private int recompense;
	private ArrayList<Entitee> protagonistes;
	private GameContainer fenetre;
	public ArrayList<String> log;
	ArrayList<Entitee>passage=new ArrayList<Entitee>();
	
	
	
	public Combat(ArrayList<Joueur> groupe,GameContainer fenetre)
	{
		this.log=new ArrayList<String>(); 
		this.fenetre=fenetre;
		this.protagonistes=new ArrayList<Entitee>();
		this.round=0;
		for(int i=0;i<groupe.size();i++)
		{
			this.protagonistes.add(groupe.get(i));
			this.protagonistes.get(i).setX(fenetre.getWidth()/6);
			this.protagonistes.get(i).setY(fenetre.getHeight()/5+(i*(fenetre.getHeight()/8))+3*fenetre.getHeight()/12);
			
		}
		this.recompense=0;
	}
	
	public int getRecomp()
	{
		return this.recompense;
	}
	
	public ArrayList<Entitee> getProta()
	{
		return this.protagonistes;
	}
	
	private Entitee factoryMonstres(int id,int nom,int moy) throws SlickException
	{
		if(id<=25)
		{
			return new Squelette(moy,nom+1);
		}
		else if(id>25 && id<=35)
		{
			return new Liche(moy,nom+1);
		}
		else
		{
			return new DarkKnight(moy,nom+1);
		}
	}
	
	
	private void GenererMonstres(int moy,int debut) throws SlickException
	{
		Random rand=new Random();
		int nb=rand.nextInt(4)+1;
		
		for(int i=0;i<nb;i++)
		{
			this.protagonistes.add(factoryMonstres(rand.nextInt(40),i,moy));
			this.protagonistes.get(debut+i).setX((fenetre.getWidth()/4)*3);
			this.protagonistes.get(debut+i).setY(fenetre.getHeight()/5+(i*(fenetre.getHeight()/8))+3*fenetre.getHeight()/12);
			
			
		}
		this.recompense=10*nb*moy;
		
	}
	
	
	
	public void debutCombat(int difficult) throws SlickException
	{
		int moyenneNiveau=0;
		int i;
		int debut=this.protagonistes.size();
		for(i=0;i<this.protagonistes.size();i++)
		{
			moyenneNiveau=moyenneNiveau+this.protagonistes.get(i).getLVL();
			
		}
		moyenneNiveau=(moyenneNiveau/i)+difficult;
		GenererMonstres(moyenneNiveau,debut);
		
		
	}
	
	private ArrayList<Integer> Initiative()
	{
		int max=0;
		Random dice=new Random();
		ArrayList<Integer> init=new ArrayList <Integer>();
		
		for (int i=0;i<this.protagonistes.size();i++)
		{
			init.add(dice.nextInt(10));//TODO: ajouter la vitesse des entitées
		}
		
		ArrayList<Integer> ordre=new ArrayList<Integer>();
		
		for (int j=0;j<this.protagonistes.size();j++)
		{
			max=0;
			for(int k=0;k<this.protagonistes.size();k++)
			{
				if(init.get(k)>init.get(max))
				{
					max=k;
				}
			}
			
			ordre.add(max);
			init.set(max, -1);
		}
		
		return ordre;
		
	}
	public ArrayList<Entitee> ciblage(Entitee instigateur,boolean offensif)
	{
		ArrayList<Entitee> cibles=new ArrayList<Entitee>();
		
		for(int i=0;i<protagonistes.size();i++)
		{
			if(offensif==true)
			{
				if(instigateur.isFriendly()!=protagonistes.get(i).isFriendly() && protagonistes.get(i).getPV()>0)
				{
					cibles.add(protagonistes.get(i));
				}
			}
			else
			{
				if(instigateur.isFriendly()==protagonistes.get(i).isFriendly() && protagonistes.get(i).getPV()>0)
				{
					cibles.add(protagonistes.get(i));
				}
			}
		}
		
		return cibles;
		
	}
	
	public String attaque(Entitee cible,Entitee attaquant)
	{
		String log="";
		int deg=attaquant.getAtk()-cible.getDef();
		log=log+attaquant.getNom()+" attaque "+cible.getNom()+" !"+'\n';
		if(deg>0)
		{
			log=log+cible.getNom()+" subis "+deg+" points de dégats !"+'\n';
			log=log+cible.getDegats(deg);
		}
		else
		{
		log=log+"Mais il n'inflige aucun dégats !";
		}
		return log;
	}
	
	private Entitee choixCible(Entitee instigateur,boolean offensif)//Interface pour que le joueur choisisse sa cible
	{
		ArrayList<Entitee> cibles=ciblage(instigateur,offensif);
		int choix=-1;
		while(choix>=cibles.size() || choix<0)
		{
		for(int i=0;i<cibles.size();i++)
		{
			System.out.println((i+1)+". "+cibles.get(i).getNom()+" LVL"+cibles.get(i).getLVL());		
		}
		Scanner sc=new Scanner(System.in);
		choix=(sc.nextInt())-1;
		
		}
		return cibles.get(choix);
		
	}
	
	
	
	public void actionIA(Entitee actif)
	{
		
		if(ciblage(actif,true).size()>0)
		{
		Action choix=IA.decision(actif, ciblage(actif,true), ciblage(actif,false));
		
		if(choix.getSort()==null)
		{
			this.log.add(attaque(choix.getCible(),actif));
		}
		
		else
		{
			this.log.add(actif.getNom()+" utilise "+choix.getSort().getNom()+'\n');
			this.log.add(choix.getCible().subirComp(choix.getSort()));
			actif.reduireMana(choix.getSort().getCout());
		}
		
		}
		
		if(actif.getEffet().size()>0)
		{
			this.log.add(actif.subirEffet());
		}
	}
	
	
	
	
	public void actionJoueur(Entitee actif,Competence sort,Entitee Cible)
	{
		
	
			if(ciblage(actif,true).size()>0) {
				
					if(sort==null)
						
					{
					this.log.add(attaque(Cible,actif));	
					
					}
					else
					{
						if(actif.getMana()<sort.getCout())
						{
							this.log.add("Pas assez de Mana !");
						}
						else
						{
						this.log.add(Cible.subirComp(sort));
						actif.reduireMana(sort.getCout());
						}
					}
					if(actif.getEffet().size()>0)
					{
						this.log.add(actif.subirEffet());
					}
					
	
				
				
				
			}
			
		
			
	}
	
	public void retirerBlesse(ArrayList<Entitee> passage)
	{
		boolean blesse=false;
		int compteur=0;
		while(blesse==false && compteur<this.protagonistes.size())
		{
			if(this.protagonistes.get(compteur).getPV()<=0)
			{
				passage.remove(this.protagonistes.get(compteur));
				this.protagonistes.remove(compteur);
				
				retirerBlesse(passage);
				blesse=true;
			}
			
			compteur++;
		}
	}
	
	
	
	
	public int conditionVictoire()
	{
		int compteur=0;
		boolean team=this.protagonistes.get(0).isFriendly();
		while(compteur<this.protagonistes.size())
		{
			if(this.protagonistes.get(compteur).isFriendly()!=team)
			{
				return 0;
			}
			
			compteur++;
		}
		
		if(team==true)
		{
			return 1;
		}
		
		else
		{
			return 2;
		}

	}
	
	
	public void mainCombat()
	{
		ArrayList<Integer> ordre;
		this.passage=new ArrayList<Entitee>();
		ordre=this.Initiative();
		for(int j : ordre)
		{
			if(this.protagonistes.get(j).getPV()>0)
			passage.add(this.protagonistes.get(j));
		}
		
		
		/*
		int victory=0;
		this.log=new ArrayList<String> ();
		ArrayList<Integer> ordre;
		ArrayList<Entitee>passage=new ArrayList<Entitee>();
		while(victory==0)
		{ 
			this.round++;
			System.out.println("###########ROUND "+this.round+"###########");
			ordre=this.Initiative();
			for(int j : ordre)
			{
				passage.add(this.protagonistes.get(j));
			}
			for(Entitee i : passage)
			{
				if(i.getPV()>0 && conditionVictoire()==0)
				{
					
					if(i.isFriendly()==true)
						{
							actionJoueur(i);
						}
					
				
					else
					{
						actionIA(i);
					}
				}
				
			}
		
			this.retirerBlesse(passage);
			
			victory=conditionVictoire();
			
			
		}
		
		if(victory == 1)
		{
			
			System.out.println("Victoire !");
			
			for(Entitee i : protagonistes)
			{
				i.getXP(this.recompense);
			}
		}
		
		if(victory==2)
		{
			System.out.println("Défaite...");
		}
		
		*/
	}
	
	
	public ArrayList<Entitee> getPassage()
	{
		return this.passage;
	}
	

}
