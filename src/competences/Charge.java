package competences;

import org.newdawn.slick.Animation;

public class Charge extends Competence {
	
	
	public Charge()
	{
		this.nom="Charge";
		this.cible=true;
		this.duree=0;
		this.zone=1;
		this.degheal=-20;
		this.deghealDurr=0;
		this.cout=25;
		this.path="src/resources/sprites/charge.png";
		this.anim=new Animation[1];
		this.x=102;
		this.y=138;
		this.tailleAnim=5;
		
	}
	
	
	
	
	
	

}
