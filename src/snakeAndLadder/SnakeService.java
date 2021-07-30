package snakeAndLadder;

import java.util.ArrayList;
import java.util.List;

public class SnakeService {

	private static List<Snake> allSnakes=null;
	
	private static SnakeService snakeService=null;
	
	private SnakeService() {
		
	}
	
	public static SnakeService getInstance() {
		if(snakeService==null) {
			allSnakes=new ArrayList<>();
			snakeService=new SnakeService();
		}
		return snakeService;
	}
	
	public void addSnake(Integer headPos,Integer tailPos) throws InvalidSnakeException {
		if(headPos<=tailPos) {
			throw new InvalidSnakeException("Head position of snake cannot be below tail");
		}
		if(headPos==100) {
			throw new InvalidSnakeException("Head position of snake cannot be at 100");
		}
		Snake snake=new Snake();
		snake.setSnakeId(SnakeIdGenerator.getId());
		snake.setHeadPos(headPos);
		snake.setTailPos(tailPos);
		allSnakes.add(snake);
	}
	
	public Integer findSnakeAtPos(Integer pos) {
		int tailPos=-1;
		for(Snake snake:allSnakes) {
			if(snake.getHeadPos()==pos) {
				tailPos=snake.getTailPos();
				break;
			}
		}
		return tailPos;
	}
}
