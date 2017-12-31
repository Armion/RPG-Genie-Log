package competences;

import java.util.ArrayList;

import org.newdawn.slick.Animation;

public class Pluie_Feu extends Competence {
	
	public Pluie_Feu()
	{
	this.nom="Boule de Feu";
	
	this.cible=true;
	this.duree=0;
	this.cout=50;
	this.zone=2;
	this.degheal=-15;
	this.deghealDurr=0;
	this.path="src/resources/sprites/feu.png";
	this.anim=new Animation[1];
	this.x=102;
	this.y=396;
	this.tailleAnim=5;
}
	
}
