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
		
		
		//merci Johann, getHeal vérifi deja qu'on depasse pas les PV max
		
		this.logs.write( new LigneLog(cible.getNom() + "est soigné de : " + amount,"Effect"));
		cible.getHeal(amount);	
		
	}
	
	
	

}
