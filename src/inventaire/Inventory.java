package inventaire;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import character.Entitee;
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
	
	public void useItem(int index, Entitee cible, String log)
	{
		if(index >= 0 && index < this.list.size())
		{
			this.list.get(index).utiliser(log, cible);
			if(this.list.get(index).getStacks() == 0)
				this.list.remove(index);
		}
		

	}
	
	public void useItem(UUID id, Entitee cible, String log)
	{
		
		Iterator<Item> it = this.list.iterator();
		Item i = it.next();
		
		while(it.hasNext() && ! i.getId().equals(id) )
		{	
			i = it.next();	
		}
		
			i.utiliser(log, cible);
			if(i.getStacks() == 0)
				this.list.remove(i);

	}
	
	public void deleteItem(UUID id)
	{
		
		Iterator<Item> it = this.list.iterator();
		Item i = it.next();
		
		while(it.hasNext() && ! i.getId().equals(id) )
		{	
			i = it.next();	
		}
			this.list.remove(i);

	}
	
	public void deleteItem(int index)
	{
		if(index >= 0 && index < this.list.size())
			this.list.remove(index);
	}
	
	
	
	
	

}
