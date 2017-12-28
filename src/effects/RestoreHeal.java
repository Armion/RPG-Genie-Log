package effects;



public class RestoreHeal extends Effect{
	
	private int amount;
	
	public RestoreHeal(int amount)
	{
		this.amount = amount;
	}

	//un effet de soin
	@Override
	public void activer() {
		
		//merci Johann, getHeal v�rifi deja qu'on depasse pas les PV max
		cible.getHeal(amount);	
	}
	
	
	

}
