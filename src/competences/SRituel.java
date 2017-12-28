package competences;

import java.util.ArrayList;

public class SRituel extends Competence {
	

	public SRituel()
	{
		this.nom="Sombre Rituel";
		this.bolus= new ArrayList<Integer>();
		this.bolus.add(3);
		this.bolus.add(3);
		this.cible=false;
		this.duree=3;
		this.cout=30;
		this.zone=1;
		this.degheal=15;
		this.deghealDurr=5;
		this.cout=10;
	}

}
