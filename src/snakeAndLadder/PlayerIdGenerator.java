package snakeAndLadder;

public class PlayerIdGenerator {

	private static Integer id=0;
	
	public static String getId() {
		return String.valueOf(id++);
	}
}
