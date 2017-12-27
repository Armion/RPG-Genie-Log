package Combat;
import java.util.ArrayList;

import Combat.competences.Competence;
import character.Clone;
import character.Entitee;

public class Action{
		
	private	Competence utilise;
	private	Entitee cible;
	private int menace;
		
		
		public Action(Competence comp, Entitee ciblage,int menace)
		{
			this.utilise=comp;
			this.cible=ciblage;
			this.menace=menace;
			}
		
		
		public Competence getSort()
		{
			return this.utilise;
		}
		
		public Entitee getCible()
		{
			return this.cible;
		}
		
		public int getMenace()
		{
			return this.menace;
		}
		
		
	}
	