package effects;
import java.util.ArrayList;

public class FightEffect extends Effect{
	
	private ArrayList<Integer> bolus;//0=bolus sur l'atk, 1=bolus sur la def
	private int degheal;
	

	public FightEffect(int duree,ArrayList<Integer> bolus,int degheal,String nom)
	{
		this.duree=duree;
		this.bolus=bolus;
		this.degheal=degheal;
		this.name=nom;
	}
	
	
	public String getNom()
	{
		return super.getName();
	}
	
	
	public int getDuree()
	{
		return this.duree;
	}
	
	public int getDegheal()
	{
		return this.degheal;
	}
	
	public ArrayList<Integer> getBolus()
	{
		return this.bolus;
	}


	@Override
	public void activer(String log) 
	{
		//nothing to do
	}
	
}
