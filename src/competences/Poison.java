package competences;

import java.util.ArrayList;

import org.newdawn.slick.Animation;

public class Poison extends Competence {
	
	
	
	public Poison()
	{
		this.nom="Poison";
		this.cible=true;
		this.duree=3;
		this.cout=10;
		this.zone=1;
		this.degheal=-5;
		this.deghealDurr=-8;
		this.cout=10;
		this.path="src/resources/sprites/poison.png";
		this.tailleAnim=5;
		this.anim=new Animation[1];
		this.x=102;
		this.y=138;
	}

}
