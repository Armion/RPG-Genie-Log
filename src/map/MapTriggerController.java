package map;

import org.newdawn.slick.SlickException;

//classe qui sert à controler les events de la map
public class MapTriggerController {

	//il a besoin de la map et de la représentation du joueur
	private Map map;
	private MapPlayer player;

	//constructeur
	public MapTriggerController(Map map, MapPlayer player) {
		this.map = map;
		this.player = player;
	}

	//
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
				else if("PNJ".equals(this.map.getObjectType(objectID)))
				{
					
				}
			}
		}
	}

	//methode pour verifier si un trigger declanche un event
	private boolean isInTrigger(int id) {
		//on regarde si la position du joueur est comprise dans le trigger, si oui on retourne true
		return this.player.getX() > this.map.getObjectX(id)
				&& this.player.getX() < this.map.getObjectX(id) + this.map.getObjectWidth(id)
				&& this.player.getY() > this.map.getObjectY(id)
				&& this.player.getY() < this.map.getObjectY(id) + this.map.getObjectHeight(id);
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
			this.map.changeMap("resources/map/" + newMap);
		}
	}

}
