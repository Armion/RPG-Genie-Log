package character;

import java.util.ArrayList;

import competences.Competence;
import effects.FightEffect;

public class Clone extends Entitee {

	
	
	
	
	
	public Clone(String nom,int pvMAx,int pv,int atk,int def,int lvl,ArrayList<FightEffect> zffets,int manaMax,int mana,ArrayList<Competence> sorts) 
	{
		this.nom=nom;
		this.pvMax=pvMAx;
		this.pv=pv;
		this.atk=atk;
		this.def=def;
		this.lvl=lvl;
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
	public void getDegats(int deg)
	{
		this.pv=this.pv-deg;
		
		if(this.pv>0)
		{
			
		}
		else 
		{
			
			
		}
	}
	
	
	
	
	@Override
	public void getHeal(int heal)
	{
		this.pv=this.pv+heal;		
		if(this.pv>this.pvMax)
		{
			this.pv=this.pvMax;
		}
		
	}
	
	
	public void subirCompe(Competence sort)
	{ 
		
		if (sort.getDuree()>0)
		{
			this.effets_subis.add(new FightEffect(sort.getDuree(),sort.getBolus(),sort.getDegDurr(),sort.getNom()));
			if(sort.getBolus()!=null)
			{
			this.atk=this.atk+sort.getBolus().get(0);
			this.def=this.def+sort.getBolus().get(1);
			}
			
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
		
		
		
	}

}
