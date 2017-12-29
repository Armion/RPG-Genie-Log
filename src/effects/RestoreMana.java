package effects;

import character.Entitee;
import singleton.log.LigneLog;

public class RestoreMana extends Effect{
	
private int amount;
	
	public RestoreMana(int amount, Entitee cible)
	{
		super(cible);
		this.amount = amount;
	}

	//un effet de soin
	@Override
	public void activer() {
		
		this.logs.write( new LigneLog(cible.getNom() + " retrouve : " + amount + " points de mana"));
		
		//merci Johann, getHeal vérifi deja qu'on depasse pas les PV max
		cible.recupMana(amount);
	}

}
