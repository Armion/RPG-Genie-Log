package competences;

import java.util.ArrayList;

import org.newdawn.slick.Animation;

public class Saignement extends Competence {
	
	

	public Saignement()
	{
		this.nom="Saignement";
		this.cible=true;
		this.duree=3;
		this.cout=10;
		this.zone=1;
		this.degheal=-10;
		this.deghealDurr=-5;
		this.cout=10;
		this.bolus=new ArrayList<Integer>();
		this.bolus.add(-5);
		this.bolus.add(-5);
		this.path="src/resources/sprites/saignement.png";
		this.tailleAnim=5;
		this.anim=new Animation[1];
		this.x=102;
		this.y=138;
	}

}
