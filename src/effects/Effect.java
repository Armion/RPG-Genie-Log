package effects;

import character.Entitee;

public abstract class Effect {
	
	protected String name;
	protected int duree; //0=instant, 1=1 round
	protected Entitee cible;
	
	
	public abstract void activer();
	
	final public String getName()
	{
		return this.name;
	}

	public void reduireDurée()
	{
		if(this.duree > 0)
			this.duree--;
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
