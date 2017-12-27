package Combat.personnages;

import java.util.ArrayList;

import Combat.competences.Competence;
import Combat.competences.Effet;

public class Clone extends Entitee {

	
	
	
	
	
	public Clone(String nom,int pvMAx,int pv,int atk,int def,int lvl,ArrayList<Effet> zffets,int manaMax,int mana,ArrayList<Competence> sorts) 
	{
		this.nom=nom;
		this.pvMax=pvMAx;
		this.pv=pv;
		this.atk=atk;
		this.def=def;
		this.lvl=lvl;
		this.effets_subis=zffets;
		this.manaMax=manaMax;
		this.mana=mana;
		this.sorts=sorts;
		
	}

	@Override
	public boolean isFriendly() {
		
		return false;
	}

	@Override
	public void getXP(int xp) {
		

	}
	
	@Override
	public String getDegats(int deg)
	{
		this.pv=this.pv-deg;
		
		if(this.pv>0)
		{
			return "";
		}
		else 
		{
			
			return "";
		}
	}
	
	
	
	
	@Override
	public String getHeal(int heal)
	{
		this.pv=this.pv+heal;		
		if(this.pv>this.pvMax)
		{
			this.pv=this.pvMax;
		}
		return "";
	}
	
	@Override
	public String subirComp(Competence sort)
	{ 
		
		if (sort.getDuree()>0)
		{
			this.effets_subis.add(new Effet(sort.getDuree(),sort.getBolus(),sort.getDegDurr(),sort.getNom()));
			this.atk=this.atk+sort.getBolus().get(0);
			this.def=this.def+sort.getBolus().get(1);
			
		}
		
		
		
			int degheal=sort.getDeg();
			if(degheal<0)
				{
					this.getDegats(-degheal);
				}
			else if(degheal>0)
				{
					this.getHeal(degheal);
				}
		
		return "";
		
	}

}
