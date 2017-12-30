package competences;

import java.util.ArrayList;

import org.newdawn.slick.Animation;

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
		this.path="src/resources/sprites/renfort.png";
		this.tailleAnim=5;
		this.anim=new Animation[1];
		this.x=102;
		this.y=138;
	}
	

}
