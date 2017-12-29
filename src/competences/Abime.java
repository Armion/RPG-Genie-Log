package competences;

import java.util.ArrayList;

public class Abime extends Competence {
	
	public Abime()
	{
		this.nom="Abime";
		this.cible=true;
		this.bolus=new ArrayList<Integer>();
		this.bolus.add(-4);
		this.bolus.add(-4);
		this.cout=30;
		this.zone=1;
		this.duree=2;
		this.degheal=-10;
		this.deghealDurr=0;
				
		
	}
	
	

}
