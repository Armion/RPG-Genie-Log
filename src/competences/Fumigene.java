package competences;

import java.util.ArrayList;

import org.newdawn.slick.Animation;

public class Fumigene extends Competence {
	
	public Fumigene()
	{
		this.nom="Fumigene";
		this.bolus= new ArrayList<Integer>();
		this.bolus.add(0);
		this.bolus.add(5);
		this.cible=false;
		this.duree=1;
		this.zone=1;
		this.degheal=2;
		this.deghealDurr=0;
		this.cout=30;
		this.path="src/resources/sprites/fumigene.png";
		this.tailleAnim=5;
		this.anim=new Animation[1];
		this.x=102;
		this.y=138;
		
	}
	

}
