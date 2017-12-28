package effects;

public abstract class Effect {
	
	protected String name;
	
	
	public abstract void activer();
	
	final public String getName()
	{
		return this.name;
	}

}
