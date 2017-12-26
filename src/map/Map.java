package map;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

//classe qui représente la map que l'on charge
public class Map {
	//on va faire de la delegation
	private TiledMap tiledMap;

	//methode init qui est appellé quand on initialise la map
	public void init() throws SlickException {
		this.tiledMap = new TiledMap("resources/map/exemple-change-map.tmx");
	}

	//cette methode affiche le background qui correspond au 3 premieres couches de la map
	public void renderBackground() {
		this.tiledMap.render(0, 0, 0);
		this.tiledMap.render(0, 0, 1);
		this.tiledMap.render(0, 0, 2);
	}

	//cette fois la partie devant le joueur (2 couches suivantes)
	public void renderForeground() {
		this.tiledMap.render(0, 0, 3);
		this.tiledMap.render(0, 0, 4);
	}

	//methode qui detecte les collision avec le joueur et un element de la map
	public boolean isCollision(float x, float y) {
		
		//on va charger la couche "logic" qui est la couche représenter les cases qui engendrent une collision
		int tileW = this.tiledMap.getTileWidth();
		int tileH = this.tiledMap.getTileHeight();
		int logicLayer = this.tiledMap.getLayerIndex("logic");
		
		//on va chercher la bonne image sur la couche des collisions
		Image tile = this.tiledMap.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
		
		//on regarde si cette case existe, mais ça ne suffit pas
		boolean collision = tile != null;
		if (collision) {
			// on récupere la couleur sur la quelle on est
			Color color = tile.getColor((int) x % tileW, (int) y % tileH);
			
			//si c'est pas transparent alors il y a collision
			collision = color.getAlpha() > 0;
		}
		return collision;
	}

	//au lieu de réinstancier un Objet map, on réinstanci juste l'objet tiledMap pour changer de map
	public void changeMap(String file) throws SlickException {
		this.tiledMap = new TiledMap(file);
	}

	//methode qui retourne le nombre d'objets (d'events) de la map
	public int getObjectCount() {
		return this.tiledMap.getObjectCount(0);
	}

	//methode qui renvoit le type de l'event
	public String getObjectType(int objectID) {
		return this.tiledMap.getObjectType(0, objectID);
	}

	//getters et setters
	public float getObjectX(int objectID) {
		return this.tiledMap.getObjectX(0, objectID);
	}

	public float getObjectY(int objectID) {
		return this.tiledMap.getObjectY(0, objectID);
	}

	public float getObjectWidth(int objectID) {
		return this.tiledMap.getObjectWidth(0, objectID);
	}

	public float getObjectHeight(int objectID) {
		return this.tiledMap.getObjectHeight(0, objectID);
	}

	public String getObjectProperty(int objectID, String propertyName, String def) {
		return this.tiledMap.getObjectProperty(0, objectID, propertyName, def);
	}

}
