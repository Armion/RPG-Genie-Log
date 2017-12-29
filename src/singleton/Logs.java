package singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Logs extends Observable{
	
	private List<String> logs;
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
	
	public void write(String newlog)
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
	
	public List<String> getLastLogs(int nb)
	{
		List<String> lastLogs = new ArrayList<>();
		
		for(int i = this.logs.size() - nb -1 ; i < this.logs.size() ; i++)
		{
			lastLogs.add(this.logs.get(i));
		}
		
		return lastLogs;
	}
	
	public String getLatestLog()
	{
		return this.logs.get(this.logs.size()-1);
	}
	
	public int getNbLogs()
	{
		return this.nbLogs;
	}

}
 