package Combat;

import java.util.Random;

import items.consommables.Pain;
import items.consommables.potions.HealPot;
import items.consommables.potions.ManaPot;
import items.equipements.Amulette;
import items.equipements.Armure_leg;
import items.equipements.Dague;
import items.equipements.Epee_courte;
import singleton.log.Logs;
import singleton.Team;
import singleton.log.LigneLog;

public class FactoryLoot {
	public FactoryLoot()
	{}
	
	
	public void earnLoot(int recom)
	{
		Random rand=new Random();
		int loot=recom;
		int limit;
		while(loot>10)
		{
			limit=rand.nextInt(loot);
			if(limit>=10 && limit<20)
			{
				Team.getInstance().getInventory().getItemsList().add(new HealPot(20));
				Logs.getInstance().write(new LigneLog("Vous obtenez potion de soin","Combat"));
			}
			else if(limit>=20 && limit <30)
			{
				Team.getInstance().getInventory().getItemsList().add(new ManaPot(20));
				Logs.getInstance().write(new LigneLog("Vous obtenez potion de mana","Combat"));
			}
			
			else if(limit>=30 && limit<40)
			{
				Team.getInstance().getInventory().getItemsList().add(new Pain());
				Logs.getInstance().write(new LigneLog("Vous obtenez pain","Combat"));
			}
			
			else if(limit>=40 && limit<60)
			{
				switch(rand.nextInt(4))
				{
				case 0:
				Team.getInstance().getInventory().getItemsList().add(new Armure_leg());
				Logs.getInstance().write(new LigneLog("Vous obtenez Armure légère","Combat"));
				break;
				case 1:
					Team.getInstance().getInventory().getItemsList().add(new Epee_courte());
					Logs.getInstance().write(new LigneLog("Vous obtenez épée courte","Combat"));
				break;
				case 2:
					Team.getInstance().getInventory().getItemsList().add(new Amulette());
					Logs.getInstance().write(new LigneLog("Vous obtenez Amulette","Combat"));
				break;
				case 3:
					Team.getInstance().getInventory().getItemsList().add(new Dague());
					Logs.getInstance().write(new LigneLog("Vous obtenez dague","Combat"));
					break;
					
				}
			}
			loot=loot-limit;
		}
		
		
	}
	
	

}
