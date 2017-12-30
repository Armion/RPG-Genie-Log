package competences;

import java.util.ArrayList;

import org.newdawn.slick.Animation;

public class LanceFlamme extends Competence {
	
	public LanceFlamme()
	{
		this.nom="Lance Flamme";
		this.cible=true;
		this.cout=30;
		this.zone=1;
		this.duree=3;
		this.degheal=-10;
		this.deghealDurr=-5;
		this.path="src/resources/sprites/sprite_yoga_flame_2.png";
		this.tailleAnim=5;
		this.anim=new Animation[1];
		this.x=102;
		this.y=138;
		
	}
	
	

}
