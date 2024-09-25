public class Block
{
    private GridDisplay display;
    private Color color;
    private Location[] locs;

    public Block(GridDisplay disp)
    {
    	display = disp;
        int shapeNum = (int)(Math.random() * 3);
        if (shapeNum == 0)
        {
            color = new Color(255, 0, 255);
            this.locs = new Location[3];
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 3);
            locs[2] = new Location(0, 5);
        }
        else if (shapeNum == 1)
        {
            color = new Color(255, 255, 0);
            this.locs = new Location[4];
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 5);
            locs[2] = new Location(1, 4);
            locs[3] = new Location(1, 5);

        }
        else
        {
            color = new Color(0, 255, 255);
            this.locs = new Location[4];
            locs[0] = new Location(0, 4);
            locs[1] = new Location(0, 3);
            locs[2] = new Location(0, 5);
            locs[3] = new Location(1, 4);
        }
        drawSelf(color);

    }

    public void drawSelf(Color color)
    {
    	for(int i = 0; i < locs.length; i++) {    	
    	display.setColor(locs[i], color);
    	}
    }

    public boolean areValidAndEmpty(Location[] locs)
    {
    	int counter = 0;
    	Color black = new Color(0,0,0);
    	for(int i = 0; i < locs.length; i++) {
    		if(display.isValid(locs[i]) == true && (display.getColor(locs[i]).equals(black))) {
    			counter++;
    		}
    	}
    	
    	if(counter == locs.length) {
    		return true;
    	} else {
        	return false;
    	}
    }

    public boolean shift(int deltaRow, int deltaCol)
    {
    	Color black = new Color(0,0,0);
    	Location[] proposed = new Location[locs.length];
    	for(int i = 0; i < locs.length; i++) {
    		display.setColor(locs[i], black);
    	}
    	for(int i = 0; i < proposed.length; i++) {
    		proposed[i] = new Location(locs[i].getRow() + deltaRow, locs[i].getCol() + deltaCol);
    	}
    	if(areValidAndEmpty(proposed) == true) {
    		locs = proposed;
        	drawSelf(color);
        	return true;
    	} else {
        	drawSelf(color);
    		return false;
    	}
    }

    public void rotate()
    {

    	Color black = new Color(0,0,0);
    	Location[] proposed = new Location[locs.length];
    	for(int i = 0; i < locs.length; i++) {
    		display.setColor(locs[i], black);
    	}
    	for(int i = 0; i < proposed.length; i++) {
    		proposed[i] = new Location(locs[0].getRow() + locs[i].getCol() - locs[0].getCol(), locs[0].getCol() + locs[0].getRow() - locs[i].getRow());
    	}
    	if(areValidAndEmpty(proposed) == true) {
    		locs = proposed;
        	drawSelf(color);
    	} else {
        	drawSelf(color);
    	}
    
    }
}