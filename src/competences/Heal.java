package competences;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Heal extends Competence{
	
	public Heal()
	{
		try {
			this.icone = new Image("resources/Icones/heal.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.nom="Heal";
		this.bolus= null;
		this.cible=false;
		this.duree=1;
		this.zone=1;
		this.degheal=19;
		this.deghealDurr=0;
		this.cout=50;
		this.tag = 3;
		this.outOfFight = true;
		this.path="src/resources/sprites/heal.png";
		this.anim=new Animation[1];
		this.x=102;
		this.y=138;
		this.tailleAnim=5;
	}
	
	

}
