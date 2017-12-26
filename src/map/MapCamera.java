/**
 * 
 */
package map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

//classe pour la camera dans la phase de map
public class MapCamera {

	private MapPlayer player;
	private float xCamera, yCamera;

	public MapCamera(MapPlayer player) {
		this.player = player;
		this.xCamera = player.getX();
		this.yCamera = player.getY();
	}

	//methode pour placer la camera à sa position
	public void place(GameContainer container, Graphics g) {
		g.translate(container.getWidth() / 2 - (int) this.xCamera, container.getHeight() / 2
				- (int) this.yCamera);
	}

	//pour mettre la camera à jour
	public void update(GameContainer container) {
		int w = container.getWidth() / 4;
		if (this.player.getX() > this.xCamera + w) {
			this.xCamera = this.player.getX() - w;
		} else if (this.player.getX() < this.xCamera - w) {
			this.xCamera = this.player.getX() + w;
		}
		int h = container.getHeight() / 4;
		if (this.player.getY() > this.yCamera + h) {
			this.yCamera = this.player.getY() - h;
		} else if (this.player.getY() < this.yCamera - h) {
			this.yCamera = this.player.getY() + h;
		}
	}

}
