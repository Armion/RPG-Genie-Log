package battle;

import org.newdawn.slick.command.Command;

//enum�ration pour les combats
public enum BattleCommand implements Command {
	ATTACK, DEFEND, FLEE, NONE
}
