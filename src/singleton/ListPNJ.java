package singleton;

import java.util.ArrayList;
import java.util.List;

import character.pnj.PNJ;

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

}
