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
		
		if(nb < this.logs.size() && nb > 0)
		{
			for(int i = this.logs.size() - nb -1 ; i < this.logs.size() ; i++)
			{
				lastLogs.add(this.logs.get(i));
			}
		}
		
		
		return lastLogs;
	}
	
	public List<LigneLog> getCombatLog()
	{
		ArrayList<LigneLog> retour=new ArrayList<LigneLog>();
		for(LigneLog i : this.logs)
		{
			if(i.getType().equals("Combat") || i.getType().equals("Effect"));
			retour.add(i);
		}
		
		return retour;
		
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
		
		
		for(Iterator<LigneLog> it= logs.iterator(); it.hasNext();)
		{
			LigneLog ligne = it.next();
			if(ligne.getType().equals(type))
			{
				it.remove();
				this.nbLogs --;
			}
		}
	}

	


}
 