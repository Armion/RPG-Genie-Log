package main;

import org.newdawn.slick.command.Command;

//enumération pour les combats
public enum BattleCommand implements Command {
	ATTACK, DEFEND, FLEE, NONE
}
