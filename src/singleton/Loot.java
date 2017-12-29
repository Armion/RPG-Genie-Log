package singleton;

import Combat.FactoryLoot;

public class Loot {
	
	private FactoryLoot fact=null;
	
	private Loot()
	{
		this.fact=new FactoryLoot();
	}

	public FactoryLoot getFact()
	{
	return this.fact;
	}
	
	private static Loot instance=new Loot();
	
	public static Loot getInstance()
	{
		return instance;
	}
	
	
}
