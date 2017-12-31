package map;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

import character.pnj.PNJ;
import singleton.ListPNJ;
import singleton.Team;

//classe qui sert à controler les events de la map
public class MapTriggerController {

	//il a besoin de la map et de la représentation du joueur
	private Map map;
	private MapPlayer player;

	//constructeur
	public MapTriggerController(Map map, MapPlayer player) 
	{
		this.map = map;
		this.player = player;
		this.initEvent();
	}


	public void update() throws SlickException {
		
		//pour les deplacements en diagonal, par defaut le joueur n'est pas sur un escalier
		this.player.setOnStair(false);
		
		//boucle qui parcourt les objets de la map
		for (int objectID = 0; objectID < this.map.getObjectCount(); objectID++) 
		{
			//on regarde si l'objet déclanche un event, et appel la bonne methode si besoin
			if (isInTrigger(objectID)) 
			{
				if ("teleport".equals(this.map.getObjectType(objectID))) {
					this.teleport(objectID);
				} else if ("stair".equals(this.map.getObjectType(objectID))) {
					this.player.setOnStair(true);
				} else if ("change-map".equals(this.map.getObjectType(objectID))) {
					this.changeMap(objectID);
				}
			}
		}
		
	}

	public void initEvent()
	{

		ListPNJ.getInstance().getListe().clear();
		
		for (int objectID = 0; objectID < this.map.getObjectCount(); objectID++) 
		{
			if(!this.map.getObjectProperty(objectID, "start", "undefined").equals("undefined"))
			{
				if(this.map.getObjectType(objectID).equals("PNJ"))
				{
					ListPNJ.getInstance().getListe().add(new PNJ(
							Float.parseFloat(this.map.getObjectProperty(objectID, "pos-x", "undefined")) ,
							Float.parseFloat(this.map.getObjectProperty(objectID, "pos-y", "undefined") ),
							this.map.getObjectName(objectID),
							this.map.getObjectProperty(objectID, "dialogue", "undefined") ));
				}
			}
		}
		
		
	}
	
	//methode pour verifier si un trigger declanche un event
	private boolean isInTrigger(int id) {
		//on regarde si la position du joueur est comprise dans le trigger, si oui on retourne true
		
		
		if(this.map.getObjectProperty(id, "start", "undefined").equals("undefined"))
		{
			
			return this.player.getX() > this.map.getObjectX(id)
					&& this.player.getX() < this.map.getObjectX(id) + this.map.getObjectWidth(id)
					&& this.player.getY() > this.map.getObjectY(id)
					&& this.player.getY() < this.map.getObjectY(id) + this.map.getObjectHeight(id);
		}
		return false;
	}

	//pour les trigger de type teleport, on va chercher les coordonnées de destination et teleport le joueur
	private void teleport(int objectID) {
		this.player.setX(Float.parseFloat(this.map.getObjectProperty(objectID, "dest-x",
				Float.toString(this.player.getX()))));
		this.player.setY(Float.parseFloat(this.map.getObjectProperty(objectID, "dest-y",
				Float.toString(this.player.getY()))));
	}

	//pour un changement de map on va charger la nouvelle map
	private void changeMap(int objectID) throws SlickException {
		this.teleport(objectID);
		String newMap = this.map.getObjectProperty(objectID, "dest-map", "undefined");
		if (!"undefined".equals(newMap)) {
			if(newMap.equals("zone_depart.tmx"))
			{
				Team.getInstance().setZone(0);
			}
			else if(newMap.equals("mine.tmx"))
			{
				Team.getInstance().setZone(1);
			}
			else if(newMap.equals("boyaux.tmx"))
			{
				Team.getInstance().setZone(2);
			}
			this.map.changeMap("resources/map/" + newMap);
		}
		this.initEvent();
		
	}
	

}
