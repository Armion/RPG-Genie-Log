package competences;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Smite extends Competence {
	
		public Smite()
		{
			try {
				this.icone = new Image("resources/Icones/smite.png");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.nom="Smite";
			this.bolus=new ArrayList<Integer>();
			this.bolus.add(-2);
			this.bolus.add(-2);
			this.cible=true;
			this.duree=2;
			this.zone=1;
			this.degheal=-19;
			this.deghealDurr=0;
			this.cout=50;
			this.path="src/resources/sprites/smite.png";
			this.x=102;
			this.y=138;
			this.tailleAnim=5;
			this.anim=new Animation[1];
		}

}
