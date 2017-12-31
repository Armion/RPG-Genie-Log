package character;
import java.util.ArrayList;
import java.util.Random;
//Sous-classes d'entit� permmettant d'impl�menter des ennemis
public abstract class Ennemi extends Entitee {
	
	protected int loot;
	protected int profil;//1=combattant,2=mage offensif,3=support;
	
	
	
	@Override
	public boolean isFriendly()
	{
		return false;
	}
	
	
	public int getProfil()
	{
		return this.profil;
	}
	

	public int getLoot()
	{
		return this.loot;
	}
			
		
		
	

	
	@Override
	public void getXP(int xp)
	{
		
	}
	
	
}
