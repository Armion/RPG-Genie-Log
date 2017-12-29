package effects;

import character.Entitee;
import singleton.log.LigneLog;

public class KillAll extends Effect {

	public KillAll(Entitee cible)
	{
		this.cible=cible;
	}
	
	@Override
	public void activer() {
		
		this.logs.write( new LigneLog(cible.getNom() + "est detruit !","Effect"));
		this.cible.getDegats(this.cible.getPVMax());
	}

}
