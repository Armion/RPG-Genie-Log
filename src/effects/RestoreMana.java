package effects;

import character.Entitee;

public class RestoreMana extends Effect{
	
private int amount;
	
	public RestoreMana(int amount, Entitee cible)
	{
		super(cible);
		this.amount = amount;
	}

	//un effet de soin
	@Override
	public void activer(String log) {
		
		log = cible.getNom() + "retrouve : " + amount + "points de mana";
		
		//merci Johann, getHeal vérifi deja qu'on depasse pas les PV max
		cible.recupMana(amount);
	}

}
