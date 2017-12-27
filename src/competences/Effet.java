package competences;
import java.util.ArrayList;

public class Effet {
	
	private int duree;//0=instant, 1=1 round
	private ArrayList<Integer> bolus;//0=bolus sur l'atk, 1=bolus sur la def
	private int degheal;
	private String nom;
	

	public Effet(int duree,ArrayList<Integer> bolus,int degheal,String nom)
	{
		this.duree=duree;
		this.bolus=bolus;
		this.degheal=degheal;
		this.nom=nom;
	}
	
	
	public String getNom()
	{
		return this.nom;
	}
	
	
	public void reduireDurée()
	{
		this.duree--;
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
	
}
