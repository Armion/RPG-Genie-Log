package competences;

import java.util.ArrayList;

public class Eclair_givre extends Competence {
	
	
	public Eclair_givre()
	{
		this.nom="Eclair de givre";
		this.bolus=new ArrayList<Integer>();
		this.bolus.add(-2);
		this.bolus.add(-2);
		this.cible=true;
		this.duree=2;
		this.cout=20;
		this.zone=1;
		this.degheal=-10;
		this.deghealDurr=0;
	}

}
