public class Game
{
    private GridDisplay display;
    private Block block;

    public Game()
    {
        display = new GridDisplay(20, 10);
        display.setTitle("Tetris");
        display.setLineColor(new Color(0, 0, 0));
        block = new Block(display);
    }

    public void play()
    {
        while (true)
        {
            //loop to process 10 key presses in half a second
            for (int i = 0; i < 10; i++)
            {
                display.pause(50);
                int key = display.checkLastKeyPressed();
                if (key == 37)
                {
                    block.shift(0, -1);//left arrow pressed

                }
                else if (key == 38)
                {
                   block.rotate(); //up arrow pressed

                }
                else if (key == 39)
                {
                    block.shift(0, 1);//right arrow pressed

                }
                else if (key == 40)
                {
                    block.shift(1, 0);//down arrow pressed

                }
            } //end of for-loop
            if(block.shift(1, 0) == false) {
                removeCompletedRows();
                block = new Block(display);
            }
            
        }
    }

  
	public boolean isCompletedRow(int row)
    {
		Color black = new Color(0,0,0);
       for(int i = 0; i < 10; i++) {
    	   if(display.getColor(new Location(row, i)).equals(black)) {
    		   return false;
    	   }
       }
       return true;
    }

    public void removeSquare(Location loc)
    {
    	for(int i = loc.getRow(); i > 0; i--) {
        	display.setColor(new Location(i, loc.getCol()), display.getColor(new Location(i-1, loc.getCol())));
    	}
    }

    public void removeRow(int row)
    {
    	for(int i = 0; i < 10; i++) {
        	removeSquare(new Location(row, i));
    	}
    }

    public void removeCompletedRows()
    {
    	for(int i= 1; i < 20; i++) {
        	if(isCompletedRow(i) == true) {
            	removeRow(i);
        	}

    	}
    }
}