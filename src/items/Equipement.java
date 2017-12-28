package items;

public abstract class Equipement extends Item {
	
	private int type; //1= armure 2=arme1main 3= arme2mains 
	private int bolusDeg;
	private int bolusDef;
	private int bolusPV;
	private int bolusMana;
	
	
	public int getBDeg()
	{
		return this.bolusDeg;
	}
	
	public int getBDef()
	{
		return this.bolusDef;
	}
	public int getBPV()
	{
		return this.bolusPV;
	}
	
	public int getBMana()
	{
		return this.bolusMana;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	
	

}
