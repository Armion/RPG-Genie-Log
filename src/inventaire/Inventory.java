package inventaire;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import character.Entitee;
import items.Item;


//classe pour représenter l'inventaire de la team
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
	
	//methode pour utiliser un item
	public void useItem(int index, Entitee cible)
	{
		//on vérifi qu'on demande un index possible
		if(index >= 0 && index < this.list.size())
		{	//on utilise l'item, et si il est egal à 0 on le retire de la liste
			this.list.get(index).utiliser(cible);
			if(this.list.get(index).getStacks() == 0)
				this.list.remove(index);
		}
		

	}
	//
	public void useItem(UUID id, Entitee cible)
	{
		
		Iterator<Item> it = this.list.iterator();
		Item i = it.next();
		
		while(it.hasNext() && ! i.getId().equals(id) )
		{	
			i = it.next();	
		}
		
			i.utiliser(cible);
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
