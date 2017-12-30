package character.pnj;

import character.Entitee;
import map.MapPlayer;

public class PNJ extends Entitee{
	
	private String dialogue;
	private float x;
	private float y;
	
	public PNJ(float x, float y , String nom, String dialogue)
	{
		super();
		this.nom = nom;
		this.x = x;
		this.y = y;
		this.dialogue = dialogue;
	}
	
	

	@Override
	public boolean isFriendly() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void getXP(int xp) {
		// TODO Auto-generated method stub
		
	}
	
	public String getDialogue()
	{
		return this.dialogue;
	}
	
	
	public float getPosX()
	{
		return this.x;
	}
	
	public float getPosY()
	{
		return this.y;
	}

}
