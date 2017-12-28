package competences;

import java.util.ArrayList;

public class Renforcement extends Competence {
	
	
	public Renforcement()
	{
		this.nom="Renforcement";
		this.cible=false;
		this.bolus=new ArrayList<Integer>();
		this.bolus.add(5);
		this.bolus.add(5);
		this.duree=3;
		this.zone=1;
		this.degheal=5;
		this.deghealDurr=5;
		this.cout=50;
	}
	

}
