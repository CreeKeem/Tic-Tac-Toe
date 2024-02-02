package project2;
import java.util.Random;

public class ComputerPlayer extends APlayer {
	
	
	public ComputerPlayer(PlayerMark mark) {
        super(mark);
    }

    @Override
    public int selectRowValue(int range) {
        // Generate a random row value within the given range
        Random random = new Random();
        return random.nextInt(range);
    }

    @Override
    public int selectColValue(int range) {
        // Generate a random column value within the given range
        Random random = new Random();
        return random.nextInt(range);
    }
		
}

