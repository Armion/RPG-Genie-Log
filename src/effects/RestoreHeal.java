package effects;

import character.Entitee;

public class RestoreHeal extends Effect{
	
	private int amount;
	
	public RestoreHeal(int amount, Entitee cible)
	{
		super(cible);
		this.amount = amount;
	}

	//un effet de soin
	@Override
	public void activer() {
		
		
		//merci Johann, getHeal vérifi deja qu'on depasse pas les PV max
		
		this.logs.write( cible.getNom() + "est soigné de : " + amount);
		cible.getHeal(amount);	
		
	}
	
	
	

}
