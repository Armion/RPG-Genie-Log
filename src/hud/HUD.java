package hud;

public abstract class HUD {
	
	protected boolean visible;
	
	
	public boolean isVisible()
	{
		return this.visible;
	}
	
	public void changeState()
	{
		if(this.visible)
			this.visible = false;
		else
			this.visible = true;
	}

}
