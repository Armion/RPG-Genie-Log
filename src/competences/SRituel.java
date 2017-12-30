package competences;

import java.util.ArrayList;

import org.newdawn.slick.Animation;

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
		this.zone=2;
		this.degheal=15;
		this.deghealDurr=5;
		this.cout=10;
		this.path="src/resources/sprites/srituel.png";
		this.tailleAnim=5;
		this.anim=new Animation[1];
		this.x=102;
		this.y=396;
	}

}
