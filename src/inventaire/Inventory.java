package inventaire;

import java.util.ArrayList;
import java.util.List;

import items.Item;

public class Inventory {
	
	
	private List<Item> list;
	private float maxWeight;
	
	
	public Inventory()
	{
		this.maxWeight = 0;
		this.list = new ArrayList<Item>();
	}
	
	public Inventory(List<Item> list, float max)
	{
		this.maxWeight = max;
		this.list = list;
	}
	
	public List<Item> getItemsList()
	{
		return this.list;
	}
	
	
	

}
