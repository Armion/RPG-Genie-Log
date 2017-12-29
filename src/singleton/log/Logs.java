package singleton.log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Logs extends Observable{
	
	private List<LigneLog> logs;
	private static Logs INSTANCE = new Logs();
	private int nbLogs;
	
	private Logs()
	{
		this.logs = new ArrayList<>();
		this.nbLogs =0;
	}
	
	
	public static Logs getInstance()
	{
		return INSTANCE;
	}
	
	public void write(LigneLog newlog)
	{
		this.logs.add(newlog);
		setChanged();
		notifyObservers();
		this.nbLogs ++;
	}
	
	public void erase(int index)
	{
		if(index > 0 && index < logs.size())
		{
			this.logs.remove(index);
			this.nbLogs = 0;
		}
		
	}
	
	public void clear()
	{
		this.logs.clear();
	}
	
	public List<LigneLog> getLastLogs(int nb)
	{
		List<LigneLog> lastLogs = new ArrayList<>();
		
		for(int i = this.logs.size() - nb -1 ; i < this.logs.size() ; i++)
		{
			lastLogs.add(this.logs.get(i));
		}
		
		return lastLogs;
	}
	
	public LigneLog getLatestLog()
	{
		return this.logs.get(this.logs.size()-1);
	}
	
	public String getLatestLogContent()
	{
		return this.logs.get(this.logs.size()-1).getContent();
	}
	
	public int getNbLogs()
	{
		return this.nbLogs;
	}
	
	public void deleteType(String type)

	{
		
		boolean blesse=false;
		int compteur=0;
		while(blesse==false && compteur<this.logs.size())
		{
			if(this.logs.get(compteur).getType().equals(type))
			{
				this.logs.remove(this.logs.get(compteur));
				this.nbLogs--;
				
				deleteType(type);
				blesse=true;
			}
			
			compteur++;
		}
		
		
		
		/*
		Iterator<LigneLog> it = this.logs.iterator();
		LigneLog ligne;
		
		while(it.hasNext())
		  {
		   ligne = it.next();
		   if(ligne.getType().equals(type))
		    this.logs.remove(ligne);
		  }*/
	}

	


}
 