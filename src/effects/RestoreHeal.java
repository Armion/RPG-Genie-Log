package effects;

import character.Entitee;
import singleton.log.LigneLog;

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
		
		
		//merci Johann, getHeal v�rifi deja qu'on depasse pas les PV max
		
		this.logs.write( new LigneLog(cible.getNom() + "est soign� de : " + amount));
		cible.getHeal(amount);	
		
	}
	
	
	

}
