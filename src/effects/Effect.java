package effects;

import character.Entitee;

public abstract class Effect {
	
	protected String name;
	protected int duree; //0=instant, 1=1 round
	protected Entitee cible;
	
	
	public abstract void activer(String log);
	
	public Effect()
	{
		this.cible = null;
		this.name = "EffectUnknow";
		this.duree = 0;
	}
	
	public Effect(Entitee cible)
	{
		this.cible = cible;
		
	}
	
	public Effect(Entitee cible, String name)
	{
		this(cible);
		this.name = name;
	}
	
	
	final public String getName()
	{
		return this.name;
	}

	public void reduireDurée()
	{
		if(this.duree > 0)
			this.duree--;
	}
	
	public void changeTarget(Entitee target)
	{
		this.cible = target;
	}
	
	public void reduireDurée(int tours)
	{
		if(this.duree > tours)
		{
			
			this.duree-= tours;
		}
		else
		{
			this.duree = 0;
		}
	}

}
