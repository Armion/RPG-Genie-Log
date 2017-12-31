package singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import character.PNJ;

public class ListPNJ {
	
	private List<PNJ> liste;
	
	
	private ListPNJ()
	{
		this.liste = new ArrayList<>();
	}
	
	private static ListPNJ INSTANCE = new ListPNJ();
	
	public static ListPNJ getInstance()
	{
		return INSTANCE;
	}
	
	
	public List<PNJ> getListe()
	{
		return this.liste;
	}
	
	public PNJ getPNJ(UUID id)
	{
		int i =0;
		while(i < this.liste.size())
		{
			if(this.liste.get(i).getid().equals(id))
				return this.liste.get(i);
			
			i++;
		}
		
		return null;
	}

}
