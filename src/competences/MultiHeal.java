package competences;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MultiHeal extends Competence{
	
	public MultiHeal()
	{
		try {
			this.icone = new Image("resources/Icones/MultiHeal.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.nom="Multi-Heal";
		this.bolus= null;
		this.cible=false;
		this.duree=1;
		this.zone=1;
		this.degheal=19;
		this.deghealDurr=0;
		this.cout=50;
		this.tag = 3;
		this.outOfFight = true;
	}

}
