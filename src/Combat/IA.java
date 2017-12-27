package Combat;
import java.util.ArrayList;

import character.Clone;
import character.Entitee;
import competences.Competence;

public class IA {

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static Action decision(Entitee actif,ArrayList<Entitee> gentils,ArrayList<Entitee> mechants)
	{
		
	
		
		 
		 ArrayList<Action> arbre=new ArrayList<Action>();
		 ArrayList<Clone> cibles=new ArrayList<Clone>();
		 
		 
		 
		 	 for(Entitee j : gentils) 
			 {
				 cibles.add(clonage(j));
			 }
		 
		 
			 for(int j=0; j<cibles.size();j++)
			 {
				 
				 cibles.get(j).getDegats(actif.getAtk()-cibles.get(j).getDef());				
				 arbre.add(new Action(null,gentils.get(j),Menace(mechants)-MenaceC(cibles)));
				 cibles.set(j, clonage(gentils.get(j)));
				 
			 }
			 
		
		  
		  
		  
		  
		  
		  
		  
		  
		 for(Competence i : actif.getComp())
		 {
			 if(i.getCout()<actif.getMana())
			 {
			 cibles=new ArrayList<Clone>();
			 
			 if(i.getCible()==true)
			 {
				 for(Entitee j : gentils) 
				 {
					 cibles.add(clonage(j));
				 }
				 
				 for(int j=0; j<cibles.size();j++)
				 {
					 
					 
					 cibles.get(j).subirComp(i);
					 
					 arbre.add(new Action(i,gentils.get(j),Menace(mechants)-MenaceC(cibles)));
					 cibles.set(j, clonage(gentils.get(j)));
					 
				 }
			 }
			 
			 else
			 {
				 for(Entitee j : mechants) 
				 {
					 cibles.add(clonage(j));
				 }
				 
				 for(int j=0; j<cibles.size();j++)
				 {
					 
					 cibles.get(j).subirComp(i);
					
					 arbre.add(new Action(i,mechants.get(j),MenaceC(cibles)-Menace(gentils)));
					 cibles.set(j, clonage(mechants.get(j)));
					 
				 } 
				 
				 
				 
			 }
			
			 }	 
		}
		 
		int choix=getIndiceMax(arbre);
		
		
		return arbre.get(choix);
		
		 
			 
			 
			 
			 
			 
			 
	}
		 
	
	
	private static int getIndiceMax(ArrayList<Action> arbre)
	{
		int max=0;
		for(int i=0;i<arbre.size();i++)
		{
			if( arbre.get(i).getMenace()> arbre.get(max).getMenace())
			{
				max=i;
			}
			
		}
		
		return max;
	}
	
	
	
	private static Clone clonage(Entitee modele)
	{
		Clone clone=new Clone(modele.getNom(),modele.getPVMax(),modele.getPV(),modele.getAtk(),modele.getDef(),modele.getLVL(),modele.getEffet(),modele.getManaMax(),modele.getMana(),modele.getSorts());
		
		return clone;
	}
	
	
	private static int Menace(ArrayList<Entitee> groupe)
	{
		int menace=0;
		
		for (Entitee i : groupe)
		{
			if(i.getPV()>0)
			{
			menace=menace+i.getPV()+i.getDef()+i.getAttack()+(i.getLVL()*5)+((int)i.getMana()/10)+(i.getComp().size()*4);
			
			}
		}
		
		
		
		return menace;
	}
	
	private static int MenaceC(ArrayList<Clone> groupe)
	{
		int menace=0;
		
		for (Entitee i : groupe)
		{
			if(i.getPV()>0)
			{
			menace=menace+i.getPV()+i.getDef()+i.getAttack()+(i.getLVL()*5)+((int)i.getMana()/10)+(i.getComp().size()*4);
			
			}
		}
		
		
		
		return menace;
	}
	
	

}
