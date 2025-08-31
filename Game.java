public class Game
{
    private GridDisplay display;
    private Block block;
    private boolean gameOver = false;  
    private long startMillis = 0L;
    private int score = 0;

    public Game()
    {
        display = new GridDisplay(20, 10);
        display.setTitle("Tetris");
        display.setLineColor(new Color(0, 0, 0));
        block = new Block(display);
    }

    public void play()
    {
        startMillis = System.currentTimeMillis();
        while (!gameOver){
            long elapsed = System.currentTimeMillis() - startMillis;
            display.setTitle("Tetris — Score " + score + " - Time - " + formatElapsed(elapsed));
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
            if (block.shift(1, 0) == false) {
                int cleared = removeCompletedRows();  
                if (cleared == 1) score += 100;
                else if (cleared == 2) score += 300;
                else if (cleared == 3) score += 500;
                else if (cleared >= 4) score += 800;

                
                if (isTopRowOccupied()) {
                    endGame();
                    break; // stops the loop
                }

                // otherwise spawn next piece
                block = new Block(display);
            }
            
        }
    }

    private String formatElapsed(long ms)
    {
        long totalSec = ms / 1000;
        long minutes = totalSec / 60;
        long seconds = totalSec % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    private boolean isTopRowOccupied()
    {
        Color black = new Color(0, 0, 0);
        int topRow = 0;
        for (int col = 0; col < 10; col++) {
            if (!display.getColor(new Location(topRow, col)).equals(black)) {
                return true;
            }
        }
        return false;
    }

    private void endGame()
    {
        gameOver = true;
        long elapsed = System.currentTimeMillis() - startMillis;
        display.setTitle("Tetris — Game Over (Score " + score + ")");
        display.pause(800);
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

    public int removeCompletedRows() {
        int cleared = 0;
        for (int y = 1; y < 20; y++) {
            if (isCompletedRow(y)) {
                removeRow(y);
                cleared++;
               
            }
        }
        return cleared;
    }
}