package competences;

import java.util.ArrayList;

import org.newdawn.slick.Animation;

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
		this.path="src/resources/sprites/abime.png";
		this.anim=new Animation[1];
		this.x=102;
		this.y=138;
		this.tailleAnim=5;
				
		
	}
	
	

}
