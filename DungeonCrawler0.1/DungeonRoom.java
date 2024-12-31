import java.awt.Color;

public class DungeonRoom {
	//~ private CartesianPoint position;
	private final int x;
    private final int y;
	private final int radius;
	private Color color;
	private boolean isSuitable;
	
	public DungeonRoom(int x, int y, int[] rgb) {
		//~ position = new CartesianPoint(x, y);
		this.x = x;
		this.y = y;
		color = new Color(rgb[0],rgb[1],rgb[2]);
		radius = 5;
		isSuitable = true;
	}
	
	public int getX() {
		return x;
    }
    
    public int getY() {
        return y;
    }
    
	public boolean getIsSuitable(){
		return isSuitable;
	}
	
	public void setIsSuitable(){
		isSuitable = true;
	}
	
	public void setIsNotSuitable(){
		isSuitable = false;
	}	
    
    public void setColor(int[] rgb){
		color = new Color(rgb[0],rgb[1],rgb[2]);
	}
	
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledRectangle(x, y, radius, radius);
		StdDraw.show();
	}
}

